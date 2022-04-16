@Service
@RequiredArgsConstructor
public class FlatographyJpqlRepositoryImpl implements FlatographyJpqlRepository {
    private final EntityManager entityManager;

    @Override
    public Page<FlatographyObjectInfo> findAll(FlatographyCriteria criteria, Pageable pageable) {
        long total = count(criteria);
        var query = createQuery(criteria);
        List<FlatographyObjectInfo> flatographyObjectInfoList = total > 0 ? query.fetch() : Collections.emptyList();

        return new RestPage<>(flatographyObjectInfoList, pageable, total);
    }

    private Long count(FlatographyCriteria criteria) {
        QFlatographyEntity qFlatographyEntity = QFlatographyEntity.flatographyEntity;

        JPQLQuery<FlatographyObjectInfo> flatographyObjectJPQLQuery = new JPAQuery<>(this.entityManager);
        return flatographyObjectJPQLQuery
                .from(qFlatographyEntity)
                .where(FlatographyPredicateConstructor.build(criteria))
                .fetchCount();
    }

    private JPQLQuery<FlatographyObjectInfo> createQuery(FlatographyCriteria criteria) {
        QFlatographyEntity qFlatographyEntity = QFlatographyEntity.flatographyEntity;
        QFlatDataEntity qFlatDataEntity = QFlatDataEntity.flatDataEntity;

        JPQLQuery<Long> flatCountQuery = new JPAQuery<>(entityManager)
                .select(qFlatDataEntity.count())
                .from(qFlatDataEntity)
                .where(qFlatDataEntity.flatography.id.eq(qFlatographyEntity.id));

        JPQLQuery<Long> flatsImplementedQuery = new JPAQuery<>(entityManager)
                .select(qFlatDataEntity.count())
                .from(qFlatDataEntity)
                .where(qFlatDataEntity.flatStatus.id.eq(FlatStatusEnum.ENCASH.getId())
                        .and(qFlatDataEntity.flatography.id.eq(qFlatographyEntity.id)));

        JPAQuery<Long> flatsLooseQuery = new JPAQuery<>(entityManager)
                .select(qFlatDataEntity.count())
                .from(qFlatDataEntity)
                .where(qFlatDataEntity.flatStatus.id.eq(FlatStatusEnum.FOR_SALE.getId())
                        .and(qFlatDataEntity.flatography.id.eq(qFlatographyEntity.id)));

        JPQLQuery<BigDecimal> totalAreaOfObjectsQuery = new JPAQuery<>(entityManager)
                .select(qFlatDataEntity.totalAreaSq.sum())
                .from(qFlatDataEntity)
                .where(qFlatDataEntity.flatography.id.eq(qFlatographyEntity.id));

        JPQLQuery<LocalDateTime> lastUpdatedDttm = new JPAQuery<>(entityManager)
                .select(qFlatDataEntity.updatedDttm.max())
                .from(qFlatDataEntity)
                .where(qFlatDataEntity.flatography.id.eq(qFlatographyEntity.id));

        JPQLQuery<FlatographyObjectInfo> flatographyObjectJPQLQuery = new JPAQuery<>(this.entityManager);
        flatographyObjectJPQLQuery.select(
                        Projections.constructor(FlatographyObjectInfo.class, qFlatographyEntity.buildingObject.id,
                                qFlatographyEntity.buildingObject.address, flatCountQuery, flatsImplementedQuery,
                                flatsLooseQuery, totalAreaOfObjectsQuery,
                                RefbookExpressionConstructor.getConstructorExpression(qFlatographyEntity.status._super),
                                qFlatographyEntity.version, lastUpdatedDttm))
                .from(qFlatographyEntity)
                .where(FlatographyPredicateConstructor.build(criteria))
                .offset(criteria.getOffset())
                .limit(criteria.getPageSize());

        criteria.getSort().get().forEach(order -> {
            OrderSpecifier<String> orderSpecifier = new OrderSpecifier<>(order.getDirection().isAscending() ? Order.ASC : Order.DESC,
                    Expressions.stringPath(order.getProperty()));
            flatographyObjectJPQLQuery.orderBy(orderSpecifier);
        });

        return flatographyObjectJPQLQuery;
    }
}
