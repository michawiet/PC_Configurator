import React, {useState} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, Dialog, AppBar, Toolbar, IconButton, Typography, Slide, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper, Container, Grid, FormControl, InputLabel, Select, MenuItem, Button} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import CloseIcon from '@material-ui/icons/Close';


const useStyles = makeStyles((theme) => ({
  appBar: {
    position: 'relative',
  },
  title: {
    marginLeft: theme.spacing(2),
    flex: 1,
  },
  container:{
    marginTop: 50,
  },
  formControl: {
    minWidth: 20,
    justifyContent: 'flex-end',
  },
}));


function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}

const rows = [
  createData('piot', 123, 6.0, 24, 4.0),
  createData('rek', 456, 9.0, 37, 4.3),
  createData('tu', 789, 16.0, 24, 6.0),
  createData('taj', 305, 3.7, 67, 4.3),
  createData('był', 356, 16.0, 49, 3.9),
];

function Basket() {
  const classes = useStyles();
  let history = useHistory();
  const [totalItems, setTotalItems] = useState(1);
  const [open, setOpen] = useState(true);
  const [openSelect, setOpenSelect] = useState(false);
  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="left" ref={ref} {...props} />;
  });
  
  const handleTotalItemsChange = (event) => {
    setTotalItems(event.target.value);
  };

  const handleCloseSelect = () => {
    setOpenSelect(false);
  };

  const handleOpenSelect = () => {
    setOpenSelect(true);
  };
  const handleClose = () => {
    history.push("/loged")
  };
  const deleteProducts = () => {
    localStorage.setItem("basket", []);
    };
  
  return (
    <div>
      <Dialog fullScreen open={open} onClose={handleClose}>
        <AppBar className={classes.appBar}>
          <Toolbar>
            <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
              <CloseIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              Koszyk
            </Typography>
            
          </Toolbar>
        </AppBar>
        <Container fixed className={classes.container}>
          <Grid container spacing={3}>
            <Grid item xs={8}>
              <TableContainer component={Paper}>
                <Table className={classes.table} size="small">
                  {/* TODO: usunąć table head */}
                  <TableHead>
                    <TableRow>
                      <TableCell>Zdjęcie:</TableCell>
                      <TableCell align="right">Nazwa:</TableCell>
                      <TableCell align="right">Cena:</TableCell>
                      <TableCell align="right">Ilość:</TableCell>
                      <TableCell align="right"><Button variant='outlined' onClick={deleteProducts}>wyczyść koszyk</Button></TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                  {rows.map((row) => (
                    <TableRow key={row.name}>
                      <TableCell component="th" scope="row">
                        {row.name}
                      </TableCell>
                      <TableCell align="right">{row.calories}</TableCell>
                      <TableCell align="right">{row.fat}</TableCell>
                      <TableCell align="right">tu kiedyś będzie ilość</TableCell>
                      <TableCell align="right"><IconButton ><DeleteIcon/></IconButton></TableCell>
                    </TableRow>
                  ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </Grid>
            <Grid item xs={4}>
              <Paper elevation={3}>
                Tutaj terz XD
              </Paper>
            </Grid>
          </Grid>
        </Container>
      </Dialog>
    </div>
  )
}

export default Basket
