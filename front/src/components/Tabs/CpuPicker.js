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
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios'

const columns = [
  { id: 'socet', label: 'Socet', minWidth: 5 ,},
  { id: 'cores', label: 'Cores', minWidth: 5, },
  {
    id: 'smt',
    label: 'Smt',
    align: 'right',
    minWidth: 50,
  },
  {
    id: 'igpu',
    label: 'integratet gpu',
    align: 'right',
  },
  {
    id: 'tdpw',
    label: 'tdpw',
    align: 'right',
  },
  {
    id: 'stp',
    label: 'st pref',
    align: 'right',
  },
  {
    id: 'mtp',
    label: 'mt pref',
    align: 'right',
  },
  {
    id: 'cc',
    label: 'corecloks',
    align: 'right',
  },
  {
    id: 'bc',
    label: 'boost cloks',
    align: 'right',
  },
];

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
  container: {
    maxHeight: 440,
  },
});






export default function StickyHeadTable() {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const MyComponent = withAxios(class MyComponentRaw extends React.Component {
    componentWillMount() {
      this.props.axios('test').then(result => {
        this.setState({ data: result.data })
      })
    }
    render() {
      const data = (this.state || {}).data
      return <div>{JSON.stringify(data)}</div>
    }
  })


  const axiosInstance = axios.create({
    baseURL: '/',
    timeout: 2000,
    headers: { 'X-Custom-Header': 'foobar' }
  });


  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

    const [userProfiles, setUserProfiles] = useState([]);
  
    const fetchUserProfiles = () => {
      axios.get("http://localhost:8080/products/cpu").then(res => {
        console.log(res);
        setUserProfiles(res.data);
      });
    };
  
    useEffect(() => {
      fetchUserProfiles();
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
            {userProfiles
            .map((userProfile) => {
                return (
                  <TableRow key = {userProfile.id}>
                    <TableCell>
                      {userProfile.socket}
                    </TableCell>
                    <TableCell>
                      {userProfile.cores}
                    </TableCell>
                    <TableCell>
                      {userProfile.smt ? "Yes" : "No"}
                    </TableCell>
                    <TableCell>
                      {userProfile.integratedGPU ? "Yes" : "No"}
                    </TableCell>
                    <TableCell>
                      {userProfile.tdpW} 
                    </TableCell>
                    <TableCell>
                      {userProfile.stPref}
                    </TableCell>
                    <TableCell>
                      {userProfile.mtPref}
                    </TableCell>
                    <TableCell>
                      {userProfile.coreClock}
                    </TableCell>
                    <TableCell>
                      {userProfile.boostClock}
                    </TableCell>
                  </TableRow>
                );
              })
            }

          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </Paper>
    </>
  );
}