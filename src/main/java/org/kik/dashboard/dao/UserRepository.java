package org.kik.dashboard.dao;

import org.kik.dashboard.model.User;

public interface UserRepository extends BaseRepository<User, Long> {
	User findFirstByUsername(String username);
}
