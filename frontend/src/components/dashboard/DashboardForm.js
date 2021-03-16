import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import {
  Grid,
  makeStyles,
} from '@material-ui/core';
import ArrowRightIcon from '@material-ui/icons/ArrowRight';
import Activity from '../activity/Activity';
import Result from '../result/Result';
import BestPosting from '../bestPosting/BestPosting';
import WeeklyTrend from '../weeklyTrend/WeeklyTrend';
import ResultTrend from '../resultTrend/ResultTrend';

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(2),
  },
}));

const DashboardForm = () => {
  const classes = useStyles();

  return (
    <Grid container spacing={3}>
      <Grid item xl={6} lg={6} sm={6} xs={12}>
        <Activity />
      </Grid>
      <Grid item xl={6} lg={6} sm={6} xs={12}>
        <Result />
      </Grid>
      <Grid item xl={3} lg={3} sm={3} xs={12}>
        <BestPosting type="Activity" count="Hours" />
      </Grid>
      <Grid item xl={3} lg={3} sm={3} xs={12}>
        <BestPosting type="Activity" count="Counts" />
      </Grid>
      <Grid item xl={3} lg={3} sm={3} xs={12}>
        <BestPosting type="Result" count="Hours" />
      </Grid>
      <Grid item xl={3} lg={3} sm={3} xs={12}>
        <BestPosting type="Result" count="Counts" />
      </Grid>
      {/* <Grid item xl={9} lg={8} md={12} xs={12}> */}
      <Grid item xs={12}>
        <WeeklyTrend />
      </Grid>
      {/* <Grid item xl={3} lg={4} md={6} xs={12}> */}
      <Grid item xs={12}>
        <ResultTrend />
      </Grid>
    </Grid>
  );
};

export default DashboardForm;
