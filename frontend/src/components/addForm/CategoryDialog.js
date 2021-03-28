import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  Button,
  IconButton,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Typography
} from '@material-ui/core';
import { changeField } from '../../modules/addForm';
import { CreateCategory } from '../../lib/api/category';
import AddCircleIcon from '@material-ui/icons/AddCircle';

const CategoryDialog = () => {
  const dispatch = useDispatch();
  const [open, setOpen] = useState(false);
  const [value, setValue] = useState('');
  const [error, setError] = useState(false);

  const handleClickOpen = (event) => {
    event.target.blur();
    setOpen(true);
  }

  const handleClose = () => {
    setValue('');
    setError(false);
    setOpen(false);
    document.activeElement.blur();
  }
  const handleChange = (event) => {
    if (error == true && event.target.value !== '') {
      setError(false);
    }
    setValue(event.target.value);
  }

  const handleSave = (event) => {
    if (value !== '' && value !== null) {
      CreateCategory(dispatch, value, handleClose);
    } else {
      setError(true);
    }
  }

  return (
    <div>
      <IconButton id="add-circle-icon" onClick={handleClickOpen}>
        <AddCircleIcon />
      </IconButton>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle disableTypography id="form-dialog-title">
          <Typography variant="h3" align="center">New Category</Typography>
        </DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please type the category name you want to create.
          </DialogContentText>
          <TextField
            autoFocus
            error={error}
            helperText={error === true ? "Category name cannot be empty." : null}
            margin="dense"
            id="categoryName"
            label="Category Name"
            value={value}
            onChange={handleChange}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleSave} color="primary" >
            Save
          </Button>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default CategoryDialog;