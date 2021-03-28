import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link as RouterLink } from 'react-router-dom';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import {
  Box,
  Card,
  CardContent,
  CardActions,
  Grid,
  Typography,
  colors,
  makeStyles,
  Chip,
  Button,
} from '@material-ui/core';
import { RecentResultList } from '../../lib/api/result';

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100%'
  },
  avatar: {
    backgroundColor: colors.red[600],
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

const RecentResult = ({ className, ...rest }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { resultList } = useSelector(({ result }) => ({
    resultList: result.recentResultList,
  }));

  useEffect(() => {
    RecentResultList(dispatch);
  }, [dispatch]);

  return (
    <Card
      className={clsx(classes.root, className)}
      {...rest}
    >
      {resultList.list !== undefined && resultList.list !== null ?
        resultList.list.map((list, index) => (
          <React.Fragment key={index}>
            <CardContent>
              <Grid
                container
                justify="space-between"
                spacing={1}
              >
                <Grid item xs={12}>
                  <Typography
                    color="textSecondary"
                    gutterBottom
                    variant="h4"
                  >
                    Most Recent Result
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
                    >
                      {list.dateAdded}
                    </Typography>
                  </Box>
                </Grid>
                <Grid item>
                  <Typography
                    color="textSecondary"
                    gutterBottom
                    variant="h4"
                  >
                    {list.content}
                  </Typography>
                  <Typography
                    color="textPrimary"
                    variant="h3"
                  >
                    {list.testDuration} mins, {list.testCount} practice(s)
            </Typography>
                </Grid>
              </Grid>
            </CardContent>
            <CardActions>
              <div style={{ width: '100%' }}>
                <RouterLink to={`/app/result`}>
                  <Box display="flex" flexDirection="row-reverse">
                    <Button size="small" color="primary" variant="text">Show All</Button>
                  </Box>
                </RouterLink>
              </div>

            </CardActions>
          </React.Fragment>
        ))
        :
        <>
          <CardContent>
            <Typography
              color="textSecondary"
              variant="h4"
              align="center"
            >
              There is no result to display. Please add one!
        </Typography>
          </CardContent>
          <CardActions>
            <div style={{ width: '100%' }}>
              <RouterLink to={`/app/result`}>
                <Box display="flex" flexDirection="row-reverse">
                  <Button size="small" color="primary" variant="text">Create New</Button>
                </Box>
              </RouterLink>
            </div>
          </CardActions>
        </>
      }

    </Card>
  );
};

RecentResult.propTypes = {
  className: PropTypes.string
};

export default RecentResult;
