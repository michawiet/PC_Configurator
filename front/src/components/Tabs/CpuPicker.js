import React, { useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import axios from 'axios';

const columns = [
  { 
    id: 'socket', 
    label: 'Socket', 
    align: 'center',
  },
  { 
    id: 'cores', 
    label: 'Cores',
    align: 'center',
  },
  {
    id: 'smt',
    label: 'SMT',
    align: 'center',
  },
  {
    id: 'igpu',
    label: 'iGPU',
    align: 'center',

  },
  {
    id: 'tdpw',
    label: 'TDP (W)',
    align: 'center',

  },
  {
    id: 'stp',
    label: 'ST pref',
    align: 'center',

  },
  {
    id: 'mtp',
    label: 'MT pref',
    align: 'center',

  },
  {
    id: 'cc',
    label: 'Core Clocks',
    align: 'center',
  },
  {
    id: 'bc',
    label: 'Boost Clocks',
    align: 'center',
  },
];

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
  container: {
    maxHeight: 650,
  },
});

export default function StickyHeadTable() {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/cpu").then(res => {
        console.log(res);
        setProducts(res.data);
      });
    };
  
    useEffect(() => {
      fetchProducts();
    }, []);
  
  return (
    <>
    <Paper className={classes.root}>
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {products
            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((userProfile) => (
                  <TableRow key = {userProfile.id}>
                    <TableCell align="center">
                      {userProfile.socket}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.cores}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.smt ? "Yes" : "No"}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.integratedGPU ? "Yes" : "No"}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.tdpW} 
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.stPref}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.mtPref}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.coreClock}
                    </TableCell>
                    <TableCell align="center">
                      {userProfile.boostClock}
                    </TableCell>
                  </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 30]}
        component="div"
        count={products.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </Paper>
    </>
  );
}