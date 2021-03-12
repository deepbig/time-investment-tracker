import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { changeField, initializeForm, login, check, openPassword } from '../../modules/auth';
import AuthForm from './AuthForm';
import Alert from '../../lib/alert';

const LoginForm = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const { form, auth, authError } = useSelector(({ auth }) => ({
    form: auth.login,
    auth: auth.auth,
    authError: auth.authError,
  }));

  const onChange = e => {
    const { value, name } = e.target;
    dispatch(
      changeField({
        form: 'login',
        key: name,
        value,
      }),
    );
  };

  const onSubmit = e => {
    e.preventDefault();
    const { username, password } = form;
    const type = "access_token";
    dispatch(login({ username, password, type }))
  }

  const openPasswordForm = () => {
    dispatch(openPassword(true));
  }

  useEffect(() => {
    dispatch(initializeForm('login'));
  }, [dispatch]);

  useEffect(() => {
    try {

      if (auth === null) {
        return;
      } else if (authError || auth.success === false) {
        if (auth.success === false) {
          setError("Bad Credentials");
        }
      } else {
        dispatch(check());
        if (form.username === "admin" && form.password === "admin") {
          Alert(1, "Your password is not secured. Please update your password.", "I will change it later.", "Change Password Now", openPasswordForm);
        }
        dispatch(initializeForm('login'));
        navigate('/app/dashboard');
      }
    } catch (err) {
      setError("Login was failed due to the internal server error.");
    }
  }, [auth, authError, dispatch]);

  return (
    <AuthForm
      type="login"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
      error={error}
    />
  )
}

export default LoginForm;