import React, { Component } from 'react';
import './AdminResult.css';

class AdminResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            results: [],
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/results')
            .then(response => response.json())
            // .then(data => console.log(data));
            .then(data => this.setState({ results: data }));
    }


    render() {
        console.info(this.state);
        const st = this.state.results;
        return (

            <div className="App">
                <header className="App-header">
                    <table border="1">
                        <tr>
                            <th>Account email</th>
                            <th>Quiz</th>
                            <th>Statistics</th>
                        </tr>

                        {st.map(item => (
                            <tr>
                                <td>{item.accountEmail}</td>
                                <td>{item.name}</td>
                                <td>{item.statistics * 100}%</td>
                            </tr>
                        ))}
                    </table>
                </header>
            </div>
        );
    }
}

export default AdminResult;
