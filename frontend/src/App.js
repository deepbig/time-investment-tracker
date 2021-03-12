import 'react-perfect-scrollbar/dist/css/styles.css';
import React, { useEffect, useState } from 'react';
import { useRoutes } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { ThemeProvider } from '@material-ui/core';
import GlobalStyles from './components/styles/GlobalStyles';
import theme from './lib/theme';
import routes from './routes';
import LoadingLogo from './components/loading/LoadingLogo';
import UpdatePasswordForm from './components/auth/UpdatePasswordForm';
import { openPassword } from './modules/auth';

const App = () => {
  const routing = useRoutes(routes);
  const dispatch = useDispatch();
  const [start, setStart] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setStart(true);
    }, 1500);
    return () => clearTimeout(timer);
  }, []);

  const { open_password } = useSelector(({ auth }) => ({
    open_password: auth.open_password
  }));

  const handlePasswordForm = () => {
    dispatch(openPassword(false));
  }

  return (
    <ThemeProvider theme={theme}>
      {/* <CssBaseline/> */}
      <LoadingLogo />
      <GlobalStyles />
      {start === true ? routing : null}
      {start === true ? open_password === true ? <UpdatePasswordForm open={open_password} onClose={handlePasswordForm} /> : null : null}
      

    </ThemeProvider>

  );
};

export default App;
