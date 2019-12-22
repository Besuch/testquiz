import React, { Component } from 'react';
import './AdminResult.css';
import Button from '@material-ui/core/Button';
import AdminAuth from '../../common/adminAuth';
import { getResultsUrl } from '../../configs';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const StyledTableCell = withStyles(theme => ({
    head: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    body: {
      fontSize: 14,
    },
  }))(TableCell);
  
  const StyledTableRow = withStyles(theme => ({
    root: {
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.background.green,
      },
    },
  }))(TableRow);

class AdminResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            results: [],
        };
    }

    componentDidMount() {
        fetch(getResultsUrl())
            .then(response => response.json())
            .then(data => this.setState({ results: data }));
    }


    render() {
        console.info(this.state);
        const st = this.state.results;

        return (
            <div className="App">
                <header className="App-header"><div className="btnDiv">
                    <Button variant="contained" size="large" onClick={() => {
                        AdminAuth.logout();
                        this.props.history.push('/')
                    }} > Logout </Button>
                </div>
                    <TableContainer component={Paper}>
                        <Table  className={makeStyles} size='small' aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell align="left">Account email</StyledTableCell>
                                    <StyledTableCell align="left">Quiz</StyledTableCell>
                                    <StyledTableCell align="left">Statistics</StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {st.map(row => (
                                    <StyledTableRow key={row.resultId}>
                                        <StyledTableCell align="left">{row.accountEmail}</StyledTableCell>
                                        <StyledTableCell align="left">{row.name}</StyledTableCell>
                                        <StyledTableCell align="left">{ Math.round(row.statistics)}%</StyledTableCell>
                                    </StyledTableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </header>
            </div>
        );
    }
}

export default AdminResult;
