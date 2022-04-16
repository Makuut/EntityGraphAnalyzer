public interface EmployeeRepository extends SoftDeleteRepository<EmployeeEntity, UUID> {

    Set<EmployeeEntity> findAllBySnils(@NotBlank String snils);
}
