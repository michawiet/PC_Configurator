import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme, AppBar, Toolbar, CssBaseline, Typography } from '@material-ui/core';
import CpuPicker from './CpuPicker';
import Button from '@material-ui/core/Button';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import { useHistory } from "react-router-dom";
import StartPanel from './StartPanel'
//icons
import MainTabsPanel from './MainTabsPanel';


//import IconSelector from './IconSelector';

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
  menuButton: {
    marginRight: 36,
  },
  hide: {
    display: 'none',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerClose: {
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: 'hidden',
    width: theme.spacing(7) + 1,
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9) + 1,
    },
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
  labelContainer: {
    width: "auto",
    padding: 0,
  },

  tabsy:{
    /*position: "absolute",
    left: 0,
  top: 140*/
  }
}));

const MiniDrawer = (props) => {

  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };
  const { children, value, index, ...other } = props;
  const [selectedTabs, setselectedTabs] = React.useState(0);

  const handleChange = (event, newValue) => {
    setselectedTabs(newValue);

  };
  function a11yProps(index) {
    return {
      id: `vertical-tab-${index}`,
      'aria-controls': `vertical-tabpanel-${index}`,
    };
  }
  function TabPanel(props) {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`vertical-tabpanel-${index}`}
        aria-labelledby={`vertical-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box p={3}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }
  let history = useHistory();
    

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed" className={clsx(classes.appBar, { [classes.appBarShift]: open,})}>
        <Toolbar>
          <Typography variant="h6" >
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
        <TabPanel value={selectedTabs} index={0}>
          <StartPanel />
        </TabPanel>
        <TabPanel value={selectedTabs} index={1}>
          <CpuPicker/>
        </TabPanel>
        <TabPanel value={selectedTabs} index={2}>
          Item Two
        </TabPanel>
        <TabPanel value={selectedTabs} index={3}>
          Item Three
        </TabPanel>
        <TabPanel value={selectedTabs} index={4}>
          Item Four
        </TabPanel>
        <TabPanel value={selectedTabs} index={5}>
          Item Five
        </TabPanel>
        <TabPanel value={selectedTabs} index={6}>
          Item Six
        </TabPanel>
        <TabPanel value={selectedTabs} index={7}>
          Item Seven
        </TabPanel>
        <TabPanel value={selectedTabs} index={8}>
          Zasilacz
        </TabPanel>
          {/**<ComponentTable></ComponentTable>**/}
          {/* component from router */}
      </main>
    </div>
  );
}

export default MiniDrawer;
