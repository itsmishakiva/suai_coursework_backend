package suai.coursework.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.auth.Role;
import suai.coursework.auth.domain.models.auth.RoleName;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
