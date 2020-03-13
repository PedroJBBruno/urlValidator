package com.pedrojbbruno.urlValidator.controllers

import com.pedrojbbruno.urlValidator.dto.AccessDto
import com.pedrojbbruno.urlValidator.dto.UserDto
import com.pedrojbbruno.urlValidator.entities.User
import com.pedrojbbruno.urlValidator.repositories.UserRepository
import com.pedrojbbruno.urlValidator.services.UrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class UserController(val userRepository: UserRepository, val urlService: UrlService) {
    @GetMapping("/validateUserAccess")
    @ResponseStatus(HttpStatus.OK)
    fun validateAccess(@RequestBody validateUser: UserDto): AccessDto {
        return AccessDto(urlService.validateUrl(validateUser.urlToValidate!!,userRepository.findById(validateUser.id!!).get().urlsAllowedRegex))
    }

    @PostMapping("/")
    fun newUser(@RequestBody newUser: UserDto): User {
        return userRepository.save(User(userName = newUser.userName!!, urlsAllowedRegex = urlService.generateUrlRegex(newUser.urls!!)))
    }
}