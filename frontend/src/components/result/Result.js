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
import { ResultList, DeleteResult } from '../../lib/api/result';
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
 * Most recent result:
 * Category name (clip), username, date added
 * Contents
 * result count, duration
 * 
 * 1 card devides by devider.
 * Show more button to get more post.
 */
const Result = ({ className, ...rest }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { resultList } = useSelector(({ result }) => ({
    resultList: result.resultList,
  }));

  useEffect(() => {
    ResultList(dispatch);
  }, [dispatch]);

  const handleDeleteAlert = (id) => {
    Alert(1, `Are you sure to delete this posting?`, 'Cancel', "Yes", () => { handleDelete(id) });
    document.getElementById("alert-button-1").focus();
  }

  const handleDelete = (id) => {
    DeleteResult(dispatch, id);
  }

  // const _handleDeleteNode = (id) => {
  //   axios.post(`/node/${id}/delete`)
  //     .then(res => {
  //       if (res === "loginRequired") {
  //         dispatch(openForm(true));
  //         return;
  //       } else if (res.data === undefined) {
  //         return;
  //       } else if (res.data.success === true) {
  //         NodeList(dispatch, filter);
  //         Alert(0, `Node is successfully deleted`, 'Okay', null, null);
  //         document.getElementById("alert-button-0").focus();
  //       } else {
  //         Alert(0, res.data.msg, 'Okay', null, null);
  //         document.getElementById("alert-button-0").focus();
  //       }
  //     })
  // }

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
                    {list.testDuration} mins, {list.testCount} practice(s)
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
            There is no result to display. Please add one!
          </Typography>
        </CardContent>
      }

    </Card>
  );
};

Result.propTypes = {
  className: PropTypes.string
};

export default Result;
