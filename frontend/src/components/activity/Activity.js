import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
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
  Divider,
  IconButton
} from '@material-ui/core';
import { ActivityList, DeleteActivity } from '../../lib/api/activity';
import DeleteIcon from '@material-ui/icons/Delete';
import Alert from '../../lib/alert';

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

/**
 * Most recent activity:
 * Category name (clip), username, date added
 * Contents
 * activity count, duration
 * 
 * 1 card devides by devider.
 * Show more button to get more post.
 */
const Activity = ({ className, ...rest }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { activityList } = useSelector(({ activity }) => ({
    activityList: activity.activityList,
  }));

  useEffect(() => {
    ActivityList(dispatch);
  }, [dispatch]);

  const handleDeleteAlert = (id) => {
    Alert(1, `Are you sure to delete this node?`, 'Cancel', "Yes", () => { handleDelete(id) });
    document.getElementById("alert-button-1").focus();
  }

  const handleDelete = (id) => {
    DeleteActivity(dispatch, id);
  }
  
  return (
    <Card
      className={clsx(classes.root, className)}
      {...rest}
    >
      {activityList.list !== undefined && activityList.list !== null ?
        activityList.list.map((list, index) => (
          <React.Fragment key={index}>
            <CardContent>
              <Grid
                container
                justify="space-between"
                spacing={1}
              >
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
                      {list.dateAdded !== null ? (new Date(list.dateAdded)).toLocaleString() : null }
                    </Typography>
                    <IconButton onClick={(e) => handleDeleteAlert(list.id)}>
                      <DeleteIcon fontSize="small" />
                    </IconButton>
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
                    {list.practiceDuration} mins, {list.activityCount} practice(s)
            </Typography>
                </Grid>
              </Grid>
            </CardContent>
            <Divider />
          </React.Fragment>
        ))
        :
        <CardContent>
          <Typography
            color="textSecondary"
            variant="h4"
            align="center"
          >
            There is no activity to display. Please add one!
        </Typography>
        </CardContent>
      }

    </Card>
  );
};

Activity.propTypes = {
  className: PropTypes.string
};

export default Activity;
