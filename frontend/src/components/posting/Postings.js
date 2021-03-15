import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import {
  Box,
  Button,
  Paper,
  Typography,
  makeStyles,
} from '@material-ui/core';
import ArrowRightIcon from '@material-ui/icons/ArrowRight';

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(2),
  },
}));

const Postings = () => {
  const classes = useStyles();

  return (
    <Box p={1}
      display="fixed"
      flexDirection="column"
      // key={list.task_id}
    >
      {/* <Paper className={classes.paper} >
        <Typography
          color="textPrimary"
          gutterBottom
          variant="h4"
        >
        
          TEST TEXT
        </Typography>
        <Box
          display="flex"
          justifyContent="flex-end"
        >
          <RouterLink to={`/app/dashboard/${list.task_id}/${list.task_name}`}>
          <RouterLink to={`/app/dashboard`}>
            <Button
              color="primary"
              endIcon={<ArrowRightIcon />}
              size="large"
              variant="text"
            >
              View all
              </Button>
          </RouterLink>
        </Box>
      </Paper> */}
    </Box>
  );
};

export default Postings;
