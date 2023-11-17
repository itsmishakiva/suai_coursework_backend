package suai.coursework.auth.dao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.Role;
import suai.coursework.auth.domain.models.RoleName;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
