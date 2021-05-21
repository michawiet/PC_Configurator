import { Container, Typography } from '@material-ui/core';
import Box from '@material-ui/core/Box';
import CpuPicker from './CpuPicker';
import StartPanel from '../StartPanel';

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
            <Typography>{children}</Typography>
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
    </Container>
  )
}

export default PartPickerTabs
