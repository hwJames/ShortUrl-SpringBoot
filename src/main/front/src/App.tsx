import React, { ReactElement, useEffect, useState } from 'react';
import ShortUrl from './shorturl.svg';
import './App.css';

const App = (): ReactElement => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                setMessage(message);
            });
    }, []);

    return (
        <div className="App">
            <div className="Content">
                <img src={ShortUrl} alt="ShortUrl" />
                <h1 className="App-title">{message}</h1>
            </div>
        </div>
    );
};

export default App;
