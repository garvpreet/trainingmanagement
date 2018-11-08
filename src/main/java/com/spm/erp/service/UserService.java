package com.spm.erp.service;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Login;
import com.spm.erp.model.User;

public interface UserService {

    String authenticateUser(Login login);

    CustomResponse registerUser(User user);

}
