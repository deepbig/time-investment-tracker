import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import {
  Box,
  Card,
  CardContent,
  Grid,
  Typography,
  colors,
  makeStyles,
  Chip,
} from '@material-ui/core';
import { BestActivityList } from '../../lib/api/activity';
import { BestResultList } from '../../lib/api/result';

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100%'
  },
  avatar: {
    backgroundColor: colors.purple[600],
    height: 56,
    width: 56
  },
  differenceIcon: {
    color: colors.red[900]
  },
  differenceValue: {
    color: colors.red[900],
    marginRight: theme.spacing(1)
  }
}));

const BestPosting = ({ className, type, count, ...rest }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { bestActivityDuration, bestActivityCount, bestResultDuration, bestResultCount } = useSelector(({ activity, result }) => ({
    bestActivityDuration: activity.bestActivityDuration,
    bestActivityCount: activity.bestActivityCount,
    bestResultDuration: result.bestResultDuration,
    bestResultCount: result.bestResultDuration,
  }))

  useEffect(() => {
    if (type === "Activity") {
      if (count === "Hours") {
        BestActivityList(dispatch, "hour");
      } else {
        BestActivityList(dispatch, "count");
      }
    } else if (type === "Result") {
      if (count === "Hours") {
        BestResultList(dispatch, "hour");
      } else {
        BestResultList(dispatch, "count");
      }
    }
  }, [dispatch, type, count])

  return (
    <Card
      className={clsx(classes.root, className)}
      {...rest}
    >
      <CardContent>
        <Grid
          container
          justify="space-between"
          spacing={1}
        >
          {type === "Activity" ?
            count === "Hours" ?
              bestActivityDuration.list !== undefined && bestActivityDuration.list !== null ?
                bestActivityDuration.list.map((list, index) => (
                  <React.Fragment key={list.id}>
                    <Grid item xs={12}>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                      >
                        Best {type} {count}
                      </Typography>
                    </Grid>
                    <Grid item xs={12}>
                      <Box
                        display="flex"
                        alignItems="center">
                        <Chip label={list.categoryName} color="primary" />
                        <Typography
                          color="textSecondary"
                          variant="h6"
                          style={{ margin: 5 }}
                          noWrap
                        >
                          {list.dateAdded !== null ? (new Date(list.dateAdded)).toLocaleString() : null }
                        </Typography>
                      </Box>
                    </Grid>
                    <Grid item>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                        noWrap
                      >
                        {list.content}
                      </Typography>
                      <Typography
                        color="textPrimary"
                        variant="h3"
                      >
                        {list.practiceDuration} mins
                      </Typography>
                    </Grid>
                  </React.Fragment>
                ))
                : // empty state
                <Grid item>
                  <Typography
                    color="textSecondary"
                    variant="h4"
                    align="center"
                  >
                    There is no activity to display. Please add one!
                  </Typography>
                </Grid>
              : // count state
              bestActivityCount.list !== undefined && bestActivityCount.list !== null ?
                bestActivityCount.list.map((list, index) => (
                  <React.Fragment key={list.id}>
                    <Grid item xs={12}>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                      >
                        Best {type} {count}
                      </Typography>
                    </Grid>
                    <Grid item xs={12}>
                      <Box
                        display="flex"
                        alignItems="center">
                        <Chip label={list.categoryName} color="primary" />
                        <Typography
                          color="textSecondary"
                          variant="h6"
                          style={{ margin: 5 }}
                          noWrap
                        >
                          {list.dateAdded !== null ? (new Date(list.dateAdded)).toLocaleString() : null }
                        </Typography>
                      </Box>
                    </Grid>
                    <Grid item>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                        noWrap
                      >
                        {list.content}
                      </Typography>
                      <Typography
                        color="textPrimary"
                        variant="h3"
                      >
                        {list.activityCount} practice(s)
                      </Typography>
                    </Grid>
                  </React.Fragment>
                ))
                : // empty state (activity count)
                <Grid item>
                  <Typography
                    color="textSecondary"
                    variant="h4"
                    align="center"
                  >
                    There is no activity to display. Please add one!
                </Typography>
                </Grid>
            :  // result
            count === "Hours" ?
              bestResultDuration.list !== undefined && bestResultDuration.list !== null ?
                bestResultDuration.list.map((list, index) => (
                  <React.Fragment key={list.id}>
                    <Grid item xs={12}>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                      >
                        Best {type} {count}
                      </Typography>
                    </Grid>
                    <Grid item xs={12}>
                      <Box
                        display="flex"
                        alignItems="center">
                        <Chip label={list.categoryName} color="primary" />
                        <Typography
                          color="textSecondary"
                          variant="h6"
                          style={{ margin: 5 }}
                          noWrap
                        >
                          {list.dateAdded !== null ? (new Date(list.dateAdded)).toLocaleString() : null }
                        </Typography>
                      </Box>
                    </Grid>
                    <Grid item>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                        noWrap
                      >
                        {list.content}
                      </Typography>
                      <Typography
                        color="textPrimary"
                        variant="h3"
                      >
                        {list.testDuration} mins
                  </Typography>
                    </Grid>
                  </React.Fragment>
                ))
                : // empty state (result hour);
                <Grid item>
                  <Typography
                    color="textSecondary"
                    variant="h4"
                    align="center"
                  >
                    There is no result to display. Please add one!
                  </Typography>
                </Grid>
              : // count (result)
              bestResultCount.list !== undefined && bestResultCount.list !== null ?
                bestResultCount.list.map((list, index) => (
                  <React.Fragment key={list.id}>
                    <Grid item xs={12}>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                      >
                        Best {type} {count}
                      </Typography>
                    </Grid>
                    <Grid item xs={12}>
                      <Box
                        display="flex"
                        alignItems="center">
                        <Chip label={list.categoryName} color="primary" />
                        <Typography
                          color="textSecondary"
                          variant="h6"
                          style={{ margin: 5 }}
                          noWrap
                        >
                          {list.dateAdded !== null ? (new Date(list.dateAdded)).toLocaleString() : null }
                        </Typography>
                      </Box>
                    </Grid>
                    <Grid item>
                      <Typography
                        color="textSecondary"
                        gutterBottom
                        variant="h4"
                        noWrap
                      >
                        {list.content}
                      </Typography>
                      <Typography
                        color="textPrimary"
                        variant="h3"
                      >
                        {list.testCount} practice(s)
                  </Typography>
                    </Grid>
                  </React.Fragment>
                ))
                : // empty state (result hour);
                <Grid item>
                  <Typography
                    color="textSecondary"
                    variant="h4"
                    align="center"
                  >
                    There is no result to display. Please add one!
                  </Typography>
                </Grid>
          }
        </Grid>
      </CardContent>
    </Card>
  );
};

BestPosting.propTypes = {
  className: PropTypes.string
};

export default BestPosting;
