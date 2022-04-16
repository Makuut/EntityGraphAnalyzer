@Repository
@RequiredArgsConstructor
public class EmployeeJpqlRepositoryImpl implements EmployeeJpqlRepository {
    private final EntityManager entityManager;

    @Override
    public Page<Employee> findAll(EmployeeCriteria criteria) {
        var pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        var total = count(criteria);
        var query = createQuery(criteria);
        List<Employee> employeeList = total > 0 ? query.fetch() : Collections.emptyList();

        return new RestPage<>(employeeList, pageable, total);
    }

    private long count(EmployeeCriteria criteria) {
        var qEmployeeEntity = QEmployeeEntity.employeeEntity;

        var employeeEntityJPAQuery = new JPAQuery<>(this.entityManager);
        return employeeEntityJPAQuery
                .from(qEmployeeEntity)
                .where(EmployeePredicateBuilder.build(criteria))
                .fetchCount();
    }

    private JPQLQuery<Employee> createQuery(EmployeeCriteria criteria) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        QEmployeeStatusEntity qEmployeeStatusEntity = QEmployeeStatusEntity.employeeStatusEntity;
        QBankruptcyCommissionerEntity qBankruptcyCommissionerEntity = QBankruptcyCommissionerEntity.bankruptcyCommissionerEntity;

        return new JPAQuery<>(entityManager)
                .select(Projections.constructor(Employee.class,
                        qEmployeeEntity.id,
                        qBankruptcyCommissionerEntity.name,
                        qBankruptcyCommissionerEntity.surname,
                        qBankruptcyCommissionerEntity.patronymic,
                        qEmployeeEntity.snils,
                        RefbookExpressionConstructor.getConstructorExpression(qEmployeeStatusEntity._super),
                        qEmployeeEntity.addedDttm))
                .from(qEmployeeEntity)
                .leftJoin(qBankruptcyCommissionerEntity).on(qEmployeeEntity.snils.eq(qBankruptcyCommissionerEntity.snils))
                .leftJoin(qEmployeeStatusEntity).on(qEmployeeEntity.status.id.eq(qEmployeeStatusEntity.id))
                .where(EmployeePredicateBuilder.build(criteria))
                .offset(criteria.getOffset())
                .limit(criteria.getPageSize());
    }
}
