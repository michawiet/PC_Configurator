import { Container, Typography } from '@material-ui/core';
import Box from '@material-ui/core/Box';
import CpuPicker from './CpuPicker';
import StartPanel from '../StartPanel';
import CoolerPicker from './CoolerPicker';
import MotherboardPicker from './MotherboardPicker';
import RamPicker from './RamPicker';
import StoragePicker from './StoragePicker';
import GpuPicker from './GpuPicker';
import CasePicker from './CasePicker';
import PsuPicker from './PsuPicker';

function PartPickerTabs({selectedTabs}) {
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
            <Typography component={'span'}>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }

  return (
    <Container fixed>
      <TabPanel value={selectedTabs} index={0}>
        <StartPanel />
      </TabPanel>
      <TabPanel value={selectedTabs} index={1}>
        <CpuPicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={2}>
        <CoolerPicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={3}>
        <MotherboardPicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={4}>
        <RamPicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={5}>
        <StoragePicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={6}>
        <GpuPicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={7}>
        <CasePicker/>
      </TabPanel>
      <TabPanel value={selectedTabs} index={8}>
        <PsuPicker/>
      </TabPanel>
    </Container>
  )
}

export default PartPickerTabs
