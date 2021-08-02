import React, { ReactElement } from 'react';
import { Container, InputGroup, FormControl, Button } from 'react-bootstrap';
import ShortUrl from './shorturl.svg';
import './App.css';

const App = (): ReactElement => {
    return (
        <div className="App">
            <div className="Content">
                <img src={ShortUrl} alt="ShortUrl" />

                <Container style={{ marginTop: '30px' }}>
                    <InputGroup className="mb-3" size="lg">
                        <FormControl placeholder="단축할 URL를 입력해주세요 (ex: https://www.naver.com)" />
                        <Button variant="outline-light">URL 생성</Button>
                    </InputGroup>
                </Container>
            </div>
        </div>
    );
};

export default App;
