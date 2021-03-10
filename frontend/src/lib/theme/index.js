import { createMuiTheme, colors } from '@material-ui/core';
import shadows from './shadows';
import typography from './typography';

const theme = createMuiTheme({
  palette: {
    background: {
      dark: '#F4F6F8',
      default: colors.common.white,
      paper: colors.common.white
    },
    primary: {
      // main: colors.teal[700]
      main: '#9f5d6d'
    },
    secondary: {
      main: '#a96c7b'
    },
    text: {
      primary: colors.blueGrey[900],
      secondary: colors.blueGrey[600],
      white: colors.white,
    }
  },
  shadows,
  typography
});

export default theme;
