import React from 'react';
import { useSelector } from 'react-redux';
import {
  Box,
  Button,
  Container,
  TextField,
  Typography,
  Dialog,
  DialogContent,
  IconButton,
  Grid,
  Paper
} from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';

const AuthForm = ({ type, form, onChange, onSubmit, error, open, onClose }) => {

  const { user } = useSelector(({ auth }) => ({
    user: auth.user
  }));

  return (
    <>
      {type === 'login' || type === 'register' ?
        <Box
          display="flex"
          flexDirection="column"
          height="100%"
          justifyContent="center"
        >
          <Container maxWidth="sm">
            <form onSubmit={onSubmit}>
              <Box mb={3}>
                <Typography
                  color="textPrimary"
                  variant="h2"
                  align="center"
                >
                  {type === 'login' ? "Sign In" : "Register"}
                </Typography>
              </Box>
              <TextField
                autoComplete="username"
                label="Username"
                margin="normal"
                name="username"
                variant="outlined"
                required
                fullWidth
                onChange={onChange}
                value={form.username}
              />
              <TextField
                label="Password"
                margin="normal"
                name="password"
                type="password"
                variant="outlined"
                required
                fullWidth
                onChange={onChange}
                value={form.password}
              />
              {type === "register" ?
                <TextField
                  label="Password Confirm"
                  margin="normal"
                  name="passwordConfirm"
                  type="password"
                  variant="outlined"
                  required
                  fullWidth
                  onChange={onChange}
                  value={form.passwordConfirm}
                /> :
                null}
              <Box my={2}>
                <Typography
                  color="error"
                  variant="h4"
                  align="center"
                >
                  {error}
                </Typography>
              </Box>
              <Box my={2}>
                <Button
                  color="primary"
                  // disabled={isSubmitting}
                  fullWidth
                  size="large"
                  type="submit"
                  variant="contained"
                >
                  {type !== 'register' ? "Sign In Now" : "Register Now"}
                </Button>
              </Box>
            </form>
          </Container>
        </Box>
        : null}
      {type === 'adminLogin' ?
        <Dialog open={open} onClose={onClose} aria-labelledby="form-dialog-title">
          <DialogContent>

            <Box mb={3}>
              <Typography
                color="textPrimary"
                variant="h3"
                align="center"
              >
                Authentication needed to improve security.
              </Typography>
            </Box>
            <Grid container justify="flex-start" alignItems="flex-start">
              <Grid item >
                <Typography
                  color="textSecondary"
                  variant="h4"
                // align="center"
                >
                  Your username is: 
              </Typography>
              </Grid>
              <Grid item xs={1}>
              </Grid>
              <Grid item>
                <Typography
                  color="textPrimary"
                  variant="h4"
                >
                  {user.data.username}
                </Typography>
              </Grid>
            </Grid>
            <form onSubmit={onSubmit}>
              <TextField
                label="Password"
                margin="normal"
                name="password"
                type="password"
                variant="outlined"
                required
                fullWidth
                onChange={onChange}
                value={form.password}
              />
              <Box my={2}>
                <Typography
                  color="error"
                  variant="h4"
                  align="center"
                >
                  {error}
                </Typography>
              </Box>
              <Box my={2}>
                <Button
                  color="primary"
                  // disabled={isSubmitting}
                  fullWidth
                  size="large"
                  type="submit"
                  variant="contained"
                >
                  Confirm
                </Button>
              </Box>
            </form>
          </DialogContent>
        </Dialog>
        : null}
      { type === 'password' ?
        <Dialog open={open} onClose={onClose} aria-labelledby="form-dialog-title" disableEscapeKeyDown disableBackdropClick>
          <Grid container justify="flex-end" alignItems="flex-end">
            <Grid item xs={9} md={10}>
              <Paper elevation={0} >
                <Typography
                  align="center"
                  color="textPrimary"
                  variant="h2"
                >
                  Password Update
                </Typography>
              </Paper>
            </Grid>
            <Grid item >
              <IconButton onClick={onClose}>
                <CloseIcon />
              </IconButton>

            </Grid>
          </Grid>
          <DialogContent>
            <form onSubmit={onSubmit}>
              <TextField
                label="New Password"
                margin="normal"
                name="update"
                type="password"
                variant="outlined"
                required
                fullWidth
                onChange={onChange}
                value={form.update}
              />
              <TextField
                label="Confirm Password"
                margin="normal"
                name="confirm"
                type="password"
                variant="outlined"
                required
                fullWidth
                onChange={onChange}
                value={form.confirm}
              />
              <Box my={2}>
                <Typography
                  color="error"
                  variant="h4"
                  align="center"
                >
                  {error}
                </Typography>
              </Box>
              <Box my={2}>
                <Button
                  color="primary"
                  // disabled={isSubmitting}
                  fullWidth
                  size="large"
                  type="submit"
                  variant="contained"
                >
                  Update Password Now
                    </Button>
              </Box>
            </form>
          </DialogContent>
        </Dialog>
        : null}
    </>
  );
};

export default AuthForm;
