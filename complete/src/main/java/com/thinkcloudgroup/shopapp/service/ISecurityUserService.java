package com.thinkcloudgroup.shopapp.service;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
