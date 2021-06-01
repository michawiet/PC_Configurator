import React from 'react'
import { makeStyles } from '@material-ui/core';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { ReactComponent as CpuIcon } from '../icons/cpu.svg';
import { ReactComponent as CoolerIcon } from '../icons/fan.svg';
import { ReactComponent as MotherboardIcon } from '../icons/motherboard.svg';
import { ReactComponent as RamIcon } from '../icons/ram.svg';
import { ReactComponent as StorageIcon } from '../icons/hard-disk-drive.svg';
import { ReactComponent as GpuIcon } from '../icons/graphics-card.svg';
import { ReactComponent as CaseIcon } from '../icons/case.svg';
import { ReactComponent as PsuIcon } from '../icons/power-supply.svg';
import { ReactComponent as ComputerIcon } from '../icons/computer.svg';

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`,
    'aria-controls': `vertical-tabpanel-${index}`,
  };
}

function MainTabsPanel({setselectedTabs, selectedTabs}) {
  const useStyles = makeStyles((theme) => ({
    icon: {
      width: 50,
      height: 50,
    },
  }));
  const classes = useStyles();
  const handleChange = (event, newValue) => {
    setselectedTabs(newValue);
  };

  return (
    <Tabs value={selectedTabs} onChange={handleChange} aria-label="Vertical tabs example" className={classes.tabsy} centered={true}>
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<ComputerIcon className={classes.icon}/>} label=" Komputer" {...a11yProps(0)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<CpuIcon  className={classes.icon}/>} label="Procesor" {...a11yProps(1)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<CoolerIcon className={classes.icon}/>} label="Chłodzenie" {...a11yProps(2)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<MotherboardIcon className={classes.icon}/>} label="Płyta główna" {...a11yProps(3)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<RamIcon className={classes.icon}/>} label="Pamięć RAM" {...a11yProps(4)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<StorageIcon className={classes.icon}/>} label="Dysk" {...a11yProps(5)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<GpuIcon className={classes.icon}/>} label="Karta graficzna" {...a11yProps(6)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<CaseIcon className={classes.icon}/>} label="Obudowa" {...a11yProps(7)} />
      <Tab classes={{ wrapper: classes.iconLabelWrapper }} icon={<PsuIcon className={classes.icon}/>} label="Zasilacz" {...a11yProps(8)} />
    </Tabs>
  )
}

export default MainTabsPanel

