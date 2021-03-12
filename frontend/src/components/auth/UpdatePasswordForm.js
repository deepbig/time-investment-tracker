import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { changeField, initializeForm, check, password } from '../../modules/auth';
import AuthForm from '../../components/auth/AuthForm';

const UpdatePasswordForm = ({ open, onClose }) => {

  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const { form, passwordResult } = useSelector(({ auth }) => ({
    form: auth.password,
    passwordResult: auth.passwordResult,
  }));

  const onChange = e => {
    const { value, name } = e.target;
    dispatch(
      changeField({
        form: 'password',
        key: name,
        value,
      }),
    );
  };

  const onSubmit = e => {
    e.preventDefault();
    // const { current, update, confirm } = form;
    const { update, confirm } = form;

    if (update !== confirm) {
      setError("Your new password does not match.");
    } else {
      dispatch(password({ update }))
    }
  }

  useEffect(() => {
    dispatch(initializeForm('password'));
  }, [dispatch]);

  useEffect(() => {
    try {
      if (passwordResult === null || passwordResult.success === undefined) {
        return;
      } else if (passwordResult.success === false) {
        setError(passwordResult.msg);
      } else {
        dispatch(check());
        dispatch(initializeForm('login'));
        onClose();
      }
    } catch (e) {
      console.log(e);
      setError("Password update was failed due to the internal server error.");
    }
  }, [passwordResult, dispatch]);

  return (
    <AuthForm
      type="password"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
      error={error}
      open={open}
      onClose={onClose}
    />
  )
}

export default UpdatePasswordForm;