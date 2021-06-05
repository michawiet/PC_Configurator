import React from 'react';
import clsx from 'clsx';
import { makeStyles, AppBar, Toolbar, CssBaseline, Typography } from '@material-ui/core';
import Button from '@material-ui/core/Button';
import { useHistory } from "react-router-dom";
import MainTabsPanel from './Tabs/AppBarButtons';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
  },
  toolbar: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
    marginTop: 75,
  
  },
}));

const MiniDrawer = (props) => {

  const classes = useStyles();
  const [open, setOpen] = React.useState(false);

  const { children, value, index, ...other } = props;
  const [selectedTabs, setselectedTabs] = React.useState(0);

  let history = useHistory();

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed" className={clsx(classes.appBar, { [classes.appBarShift]: open,})}>
        <Toolbar>
          <Typography component={'span'} variant="h6" >
            Konfigurator PC
            {/*Title that is based on actual list selection*/}
          </Typography>
          <Button style={{ marginLeft: "84%" }} color="inherit" onClick={()=>{history.push("/login")}}>LogIn</Button>
          <Button  style={{ marginLeft: "auto" }} color="inherit" onClick={()=>{history.push("/signup")}}>SignUp</Button>
        </Toolbar>
        <MainTabsPanel selectedTabs={selectedTabs} setselectedTabs={setselectedTabs} />
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
      </main>
    </div>
  );
}

export default MiniDrawer;
