import React from 'react'
import { makeStyles, Button  } from '@material-ui/core';
import { ReactComponent as CpuIcon } from '../../icons/cpu.svg';
import { ReactComponent as CoolerIcon } from '../../icons/fan.svg';
import { ReactComponent as MotherboardIcon } from '../../icons/motherboard.svg';
import { ReactComponent as RamIcon } from '../../icons/ram.svg';
import { ReactComponent as StorageIcon } from '../../icons/hard-disk-drive.svg';
import { ReactComponent as GpuIcon } from '../../icons/graphics-card.svg';
import { ReactComponent as CaseIcon } from '../../icons/case.svg';
import { ReactComponent as PsuIcon } from '../../icons/power-supply.svg';
import { ReactComponent as ComputerIcon } from '../../icons/computer.svg';
import { useHistory } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    justifyContent: "center",
    marginTop: 4,
  },
  icon: {
    width: 50,
    height: 50,
  },
  button: {
    '&:hover': {
      boxShadow: theme.shadows[6],
    },
  }
}));

function MainTabsPanel({setselectedTabs, selectedTabs}) {
  let history = useHistory();
  const classes = useStyles();

  const buttons_props = [
    { path: '/konfigurator', startIcon: <ComputerIcon className={classes.icon}/>, title: 'Konfigurator'},
    { path: '/cpu', startIcon: <CpuIcon className={classes.icon}/>, title: 'Procesor'},
    { path: '/cooler', startIcon: <CoolerIcon className={classes.icon}/>, title: 'Chłodzenie'},
    { path: '/motherboard', startIcon: <MotherboardIcon className={classes.icon}/>, title: 'Płyta główna'},
    { path: '/ram', startIcon: <RamIcon className={classes.icon}/>, title: 'Pamięć RAM'},
    { path: '/psu', startIcon: <StorageIcon className={classes.icon}/>, title: 'Dysk'},
    { path: '/case', startIcon: <GpuIcon className={classes.icon}/>, title: 'Karta graficzna'},
    { path: '/storage', startIcon: <CaseIcon className={classes.icon}/>, title: 'Obudowa'},
    { path: '/cooler', startIcon: <PsuIcon className={classes.icon}/>, title: 'Zasilacz'},
  ];
  
  return (
  <div className={classes.root}>
    {buttons_props.map((prop) => (
      <Button
      variant="contained"
      disableElevation
      disableRipple
      color="primary"
      className={classes.button}
      startIcon={prop.startIcon}
      onClick={()=> history.push(prop.path)}
    >
      {prop.title}
    </Button>
    ))}
  </div>
  )
}

export default MainTabsPanel

