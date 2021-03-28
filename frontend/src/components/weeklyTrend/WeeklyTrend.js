import React, { useState, useEffect } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { Bar } from 'react-chartjs-2';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  useTheme,
  makeStyles,
  colors,
  FormControl,
  InputLabel,
  Select,
  MenuItem
} from '@material-ui/core';
import { useSelector, useDispatch } from 'react-redux';
import { WeeklyActivityList } from '../../lib/api/activity';
import { WeeklyResultList } from '../../lib/api/result';
import { initializeForm as initializeActivityForm } from '../../modules/activity';
import { initializeForm as initializeResultForm } from '../../modules/result';

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
}));

const WeeklyTrend = ({ type, count, className, ...rest }) => {
  const classes = useStyles();
  const theme = useTheme();
  const dispatch = useDispatch();
  const [categorySelected, setCategorySelected] = useState('');

  useEffect(() => {
    
    if (categorySelected !== undefined && categorySelected !== null && categorySelected !== "") {
      if (type === "Activity") {
        if (count === "Hours") {
          WeeklyActivityList(dispatch, "hour", categorySelected);
        } else if (count === "Counts") {
          WeeklyActivityList(dispatch, "count", categorySelected);
        }
      } else if (type === "Result") {
        if (count === "Hours") {
          WeeklyResultList(dispatch, "hour", categorySelected);
        } else if (count === "Counts") {
          WeeklyResultList(dispatch, "count", categorySelected);
        }
      }
    } 
    // else {
    //   if (type === "Activity") {
    //     if (count === "Hours") {
    //       dispatch(initializeActivityForm('weeklyActivityCountSummary'));
    //     } else if (count === "Counts") {
    //       dispatch(initializeActivityForm('weeklyActivityHourSummary'));
    //     }
    //   } else if (type === "Result") {
    //     if (count === "Hours") {
    //       dispatch(initializeResultForm('weeklyResultCountSummary'));
    //     } else if (count === "Counts") {
    //       dispatch(initializeResultForm('weeklyResultHourSummary'));
    //     }
    //   }
    // }

  }, [dispatch, categorySelected])

  const {
    categoryList,
    weeklyActivityCountSummary,
    weeklyActivityHourSummary,
    weeklyResultCountSummary,
    weeklyResultHourSummary } = useSelector(({ category, activity, result }) => ({
      categoryList: category.categoryList,
      weeklyActivityCountSummary: activity.weeklyActivityCountSummary,
      weeklyActivityHourSummary: activity.weeklyActivityHourSummary,
      weeklyResultCountSummary: result.weeklyResultCountSummary,
      weeklyResultHourSummary: result.weeklyResultHourSummary,
    }))

  const data = {
    datasets: [
      {
        backgroundColor: colors.indigo[500],
        data:
          type === "Activity" && count === "Counts" ?
            weeklyActivityCountSummary.total !== undefined && weeklyActivityCountSummary.total === 14 ?
              weeklyActivityCountSummary.list[1] : [0, 0, 0, 0, 0, 0, 0]
            : type === "Activity" && count === "Hours" ?
              weeklyActivityHourSummary.total !== undefined && weeklyActivityHourSummary.total === 14 ?
                weeklyActivityHourSummary.list[1] : [0, 0, 0, 0, 0, 0, 0]
              : type === "Result" && count === "Counts" ?
                weeklyResultCountSummary.total !== undefined && weeklyResultCountSummary.total === 14 ?
                  weeklyResultCountSummary.list[1] : [0, 0, 0, 0, 0, 0, 0]
                : type === "Result" && count === "Hours" ?
                  weeklyResultHourSummary.total !== undefined && weeklyResultHourSummary.total === 14 ?
                    weeklyResultHourSummary.list[1] : [0, 0, 0, 0, 0, 0, 0]
                  : [0, 0, 0, 0, 0, 0, 0],
        label: 'This week'
      },
      {
        backgroundColor: colors.grey[200],
        data:
          type === "Activity" && count === "Counts" ?
            weeklyActivityCountSummary.total !== undefined && weeklyActivityCountSummary.total === 14 ?
              weeklyActivityCountSummary.list[0] : [0, 0, 0, 0, 0, 0, 0]
            : type === "Activity" && count === "Hour" ?
              weeklyActivityHourSummary.total !== undefined && weeklyActivityHourSummary.total === 14 ?
                weeklyActivityHourSummary.list[0] : [0, 0, 0, 0, 0, 0, 0]
              : type === "Result" && count === "Counts" ?
                weeklyResultCountSummary.total !== undefined && weeklyResultCountSummary.total === 14 ?
                  weeklyResultCountSummary.list[0] : [0, 0, 0, 0, 0, 0, 0]
                : type === "Result" && count === "Hour" ?
                  weeklyResultHourSummary.total !== undefined && weeklyResultHourSummary.total === 14 ?
                    weeklyResultHourSummary.list[0] : [0, 0, 0, 0, 0, 0, 0]
                  : [0, 0, 0, 0, 0, 0, 0],
        label: 'Last week'
      }
    ],
    labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat']
  };

  const options = {
    animation: false,
    cornerRadius: 20,
    layout: { padding: 0 },
    legend: { display: false },
    maintainAspectRatio: false,
    responsive: true,
    scales: {
      xAxes: [
        {
          barThickness: 12,
          maxBarThickness: 10,
          barPercentage: 0.5,
          categoryPercentage: 0.5,
          ticks: {
            fontColor: theme.palette.text.secondary
          },
          gridLines: {
            display: false,
            drawBorder: false
          }
        }
      ],
      yAxes: [
        {
          ticks: {
            fontColor: theme.palette.text.secondary,
            beginAtZero: true,
            min: 0
          },
          gridLines: {
            borderDash: [2],
            borderDashOffset: [2],
            color: theme.palette.divider,
            drawBorder: false,
            zeroLineBorderDash: [2],
            zeroLineBorderDashOffset: [2],
            zeroLineColor: theme.palette.divider
          }
        }
      ]
    },
    tooltips: {
      backgroundColor: theme.palette.background.default,
      bodyFontColor: theme.palette.text.secondary,
      borderColor: theme.palette.divider,
      borderWidth: 1,
      enabled: true,
      footerFontColor: theme.palette.text.secondary,
      intersect: false,
      mode: 'index',
      titleFontColor: theme.palette.text.primary
    }
  };

  const handleChangeCategory = (event) => {
    setCategorySelected(event.target.value);
  };

  return (
    <Card
      className={clsx(classes.root, className)}
      {...rest}
    >
      <CardHeader
        action={(
          // <Button
          //   endIcon={<ArrowDropDownIcon />}
          //   size="small"
          //   variant="text"
          // >
          //   Last 7 days
          // </Button>
          <FormControl className={classes.formControl}>
            <InputLabel id="category-select-label">Category</InputLabel>
            <Select
              labelId="category-select-label"
              id="category-select"
              value={categorySelected}
              onChange={handleChangeCategory}
            >
              {categoryList.list !== undefined ?
                categoryList.list.map((list) => (
                  <MenuItem value={list.category_name}>{list.category_name}</MenuItem>
                ))
                : <MenuItem value={""}>No Option</MenuItem>}
            </Select>
          </FormControl>
        )}
        title={`Latest Weekly ${type} Trend (${count})`}
      />
      <Divider />
      <CardContent>
        <Box
          height={400}
          position="relative"
        >
          <Bar
            data={data}
            options={options}
          />
        </Box>
      </CardContent>
      {/* <Divider />
      <Box
        display="flex"
        justifyContent="flex-end"
        p={2}
      >
        <Button
          color="primary"
          endIcon={<ArrowRightIcon />}
          size="small"
          variant="text"
        >
          Overview
        </Button>
      </Box> */}
    </Card>
  );
};

WeeklyTrend.propTypes = {
  className: PropTypes.string
};

export default WeeklyTrend;
