public interface EmployeeJpqlRepository {

    Page<Employee> findAll(EmployeeCriteria criteria);
}
