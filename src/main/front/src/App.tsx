import React, { ReactElement, useState, useCallback } from 'react';
import {
    Container,
    InputGroup,
    FormControl,
    Button,
    Spinner,
} from 'react-bootstrap';
import axios from 'axios';
import ShortUrl from './shorturl.svg';
import './App.css';

const App = (): ReactElement => {
    const [url, setUrl] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const urlFormOnChange = useCallback(
        (e: React.ChangeEvent<HTMLInputElement>) => {
            if (isSuccess === true) {
                setIsSuccess(false);
            }
            setUrl(e.target.value);
        },
        [isSuccess],
    );

    const createUrl = useCallback(() => {
        setLoading(true);
        axios
            .post(`${process.env.REACT_APP_BASE_URL}/api/v1/url`, {
                url,
            })
            .then(response => {
                const { statusCode, message, data } = response.data;

                if (statusCode !== 200) {
                    setError(message);
                } else {
                    setIsSuccess(true);
                    setUrl(
                        `${process.env.REACT_APP_BASE_URL}/${data.shortUrl}`,
                    );
                }
            })
            .catch(() => setError('오류'))
            .finally(() => {
                setLoading(false);
            });
    }, [url]);

    const copyUrl = useCallback(() => {
        navigator.clipboard.writeText(url);
    }, [url]);

    return (
        <div className="App">
            <div className="Content">
                <img src={ShortUrl} alt="ShortUrl" />

                <Container style={{ marginTop: '30px' }}>
                    <InputGroup className="mb-3" size="lg">
                        <FormControl
                            value={url}
                            onChange={urlFormOnChange}
                            placeholder="단축할 URL를 입력해주세요 (ex: https://www.naver.com)"
                        />
                        {isSuccess ? (
                            <Button variant="outline-light" onClick={copyUrl}>
                                URL 복사
                            </Button>
                        ) : (
                            <Button variant="outline-light" onClick={createUrl}>
                                URL 생성
                            </Button>
                        )}
                    </InputGroup>
                </Container>

                {loading && (
                    <div className="Loading">
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    </div>
                )}
            </div>
        </div>
    );
};

export default App;
