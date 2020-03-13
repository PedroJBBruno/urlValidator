package com.pedrojbbruno.urlValidator.repositories

import com.pedrojbbruno.urlValidator.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User?,Long?>