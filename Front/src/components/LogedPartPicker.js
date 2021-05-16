import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme, Drawer, AppBar, Toolbar, List, CssBaseline, Typography, Divider, IconButton, ListItem, ListItemIcon, ListItemText } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import { withRouter } from 'react-router-dom';
import { Switch, Route } from 'react-router-dom';
import BuildViewer from './BuildViewer';
import CpuPicker from './CpuPicker';
import Button from '@material-ui/core/Button';
import ComponentTable from './ComponentTable';
import {Redirect} from 'react-router-dom';
import PropTypes from 'prop-types';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import { useHistory } from "react-router-dom";
import QuestionsPanel from './QuestionsPanel'
import StartPanel from './StartPanel'
import { grey, red, green } from '@material-ui/core/colors';

//icons
import SvgIcon from '@material-ui/core/SvgIcon';
import { ReactComponent as CpuIcon } from '../icons/066-cpu-2.svg';
import { ReactComponent as CoolerIcon } from '../icons/046-cooler.svg';
import { ReactComponent as MotherboardIcon } from '../icons/070-motherboard.svg';
import { ReactComponent as RamIcon } from '../icons/088-ram-memory.svg';
import { ReactComponent as StorageIcon } from '../icons/089-hard-drive.svg';
import { ReactComponent as GpuIcon } from '../icons/087-graphic-card.svg';
import { ReactComponent as CaseIcon } from '../icons/091-desktop.svg';
import { ReactComponent as PsuIcon } from '../icons/084-supply.svg';
import { ReactComponent as ComputerIcon } from '../icons/007-computer.svg';
import ShoppingCartOutlinedIcon from '@material-ui/icons/ShoppingCartOutlined';

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
    marginTop: 40,
  
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
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <Typography variant="h6" >
            Konfigurator PC
            {/*Title that is based on actual list selection*/}
          </Typography>
          
          <IconButton style={{ marginLeft: "84%" }} aria-label="delete">
            <ShoppingCartOutlinedIcon style={{ color: green[500] }}  fontSize="large" />
          </IconButton>
          <Button  style={{ marginLeft: "auto" }} color="inherit" onClick={()=>{history.push("/")}} >Logout</Button>
        </Toolbar>
        <Tabs
          value={selectedTabs}
          onChange={handleChange}
          aria-label="Vertical tabs example"
          className={classes.tabsy}
          centered={true}
        >
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<ComputerIcon width={25}/>} label=" Komputer" {...a11yProps(0)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<CpuIcon  width={25}/>} label="Procesor" {...a11yProps(1)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<CoolerIcon width={25}/>} label="Chłodzenie" {...a11yProps(2)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<MotherboardIcon width={25}/>} label="Płyta główna" {...a11yProps(3)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<RamIcon width={25}/>} label="Pamięć RAM" {...a11yProps(4)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<StorageIcon width={25}/>} label="Dysk" {...a11yProps(5)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<GpuIcon width={35}/>} label="Karta graficzna" {...a11yProps(6)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<CaseIcon width={25}/>} label="Obudowa" {...a11yProps(7)} />
          <Tab classes={{ wrapper: classes.iconLabelWrapper,  labelContainer: classes.labelContainer }} icon={<PsuIcon width={25}/>} label="Zasilacz" {...a11yProps(8)} />
        </Tabs>
      </AppBar>

      <main className={classes.content}>
        <div className={classes.toolbar} />
      
        <TabPanel value={selectedTabs} index={0}>
        <StartPanel/>
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
      </main>
    </div>
  );
}

export default MiniDrawer;