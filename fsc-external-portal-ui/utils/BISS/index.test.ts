import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import {
  bissGetPort,
  bissSign,
  bissGetSigner,
  BISSPorts,
  BISSSignResponse,
  BISSSignParams,
  BISSGetSignerResponse,
} from '.';

const versionData = {
  version: '2.16',
  httpMethods: 'GET, POST',
  contentTypes: 'versionData, digest',
  signatureTypes: 'signature',
  selectorAvailable: true,
  hashAlgorithms: 'SHA1, SHA256, SHA384, SHA512',
};

describe('BISS Port Utilities', () => {
  it('returns port if BISS is running', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock.onGet(`https://localhost:${port}/version`).reply(200, versionData);
    const response: any = await bissGetPort();
    expect(response.port).toEqual(port);
    expect(response.versionInfo).toEqual(versionData);
    expect(mock.history.get.length).toBe(1);
    mock.reset();
  });
  it('keeps looping through ports until found', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[BISSPorts.length - 1];
    mock.onGet(`https://localhost:${port}/version`).reply(200, versionData);
    const response: any = await bissGetPort();
    expect(response.port).toEqual(port);
    expect(response.versionInfo).toEqual(versionData);
    expect(mock.history.get.length).toBe(BISSPorts.length);
    mock.reset();
  });
  it('returns false if BISS is not running', async () => {
    const mock = new MockAdapter(axios);
    const port = await bissGetPort();
    expect(!!port).toEqual(false);
    expect(mock.history.get.length).toBe(BISSPorts.length);
    mock.reset();
  });
});

const signData: BISSSignResponse = {
  status: 'ok',
  reasonCode: '200',
  reasonText: 'Signer certificate is chosen',
  signatures: ['aidjoijqoiJOIQJEIJIOJ1251DEOIoieoiqjeQWOIEJiojsad/djq==='],
  signatureType: 'signature',
};

const signParams: BISSSignParams = {
  version: '2.12',
  contents: ['DOAKEPOQKkepqokwepqkewasd==='],
  signedContents: ['DOAKEPOQKkepqokwepqkewasd==='],
  signedContentsCert: ['DOAKEPOQKkepqokwepqkewasd==='],
  contentType: 'data',
  hashAlgorithm: 'SHA-256',
  signatureType: 'signature',
  signerCertificateB64: 'adspojqpkeqpoe1pKPQWEKQPkpqwke=-=',
};

describe('BISS sign utilities', () => {
  it('returns correct signed information', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock.onPost(`https://localhost:${port}/sign`).reply(200, signData);
    const response: any = await bissSign(port, signParams);
    expect(response).toEqual(signData);
    expect(mock.history.post.length).toBe(1);
    mock.reset();
  });
  it('returns an Axios Error if HTTP code is in 400 range', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock.onPost(`https://localhost:${port}/sign`).reply(400);
    try {
      await bissSign(port, signParams);
    } catch (e) {
      const error = e as any;
      expect(error.message).toBe('Request failed with status code 400');
      expect(error.response.status).toBe(400);
      expect(error.stack).toBeTruthy();
      expect(error.config).toBeTruthy();
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });
  it('returns an Axios Error if HTTP code is in 500 range', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock.onPost(`https://localhost:${port}/sign`).reply(500);
    try {
      await bissSign(port, signParams);
    } catch (e) {
      const error = e as any;
      expect(error.message).toBe('Request failed with status code 500');
      expect(error.response.status).toBe(500);
      expect(error.stack).toBeTruthy();
      expect(error.config).toBeTruthy();
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });
  it('Returns a custom error if HTTP code is in 200 range, but reasonCode is not in 200 range or status is failed', async () => {
    const BISSErrorResponse = {
      status: 'failed',
      reasonCode: '400',
      reasonText: 'Липсващи задължителни параметри на заявката',
    };
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock.onPost(`https://localhost:${port}/sign`).reply(200, BISSErrorResponse);
    try {
      await bissSign(port, signParams);
    } catch (e) {
      const { data: error } = e as any;
      expect(error.reasonCode).toBe(BISSErrorResponse.reasonCode);
      expect(error.reasonText).toBe(BISSErrorResponse.reasonText);
      expect(error.status).toBe(BISSErrorResponse.status);
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });
  it('returns a validation error if signatures array is empty', async () => {
    const emptyChainSignerData: BISSSignResponse = {
      ...signData,
      signatures: [],
    };
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];
    mock
      .onPost(`https://localhost:${port}/sign`)
      .reply(200, emptyChainSignerData);
    try {
      await bissSign(port, signParams);
    } catch (e) {
      const error = e as any;
      expect(error.message).toEqual('no signatures in response');
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });
});

const getSignerData: BISSGetSignerResponse = {
  status: 'ok',
  reasonCode: '200',
  reasonText: 'Signer certificate is chosen',
  chain: ['aidjoijqoiJOIQJEIJIOJ1251DEOIoieoiqjeQWOIEJiojsad/djq==='],
};

describe('BISS getSigner utilities', () => {
  it('returns correct signer information', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];

    mock
      .onPost(`https://localhost:${port}/getsigner`)
      .reply(200, getSignerData);

    const response: any = await bissGetSigner(port, { selector: {} });

    expect(response).toEqual(getSignerData);
    expect(mock.history.post.length).toBe(1);
    mock.reset();
  });

  it('returns an Axios Error if HTTP code is in 400 range', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];

    mock.onPost(`https://localhost:${port}/getsigner`).reply(400);

    try {
      await bissGetSigner(port, { selector: {} });
    } catch (e) {
      const error = e as any;
      expect(error.message).toBe('Request failed with status code 400');
      expect(error.response.status).toBe(400);
      expect(error.stack).toBeTruthy();
      expect(error.config).toBeTruthy();
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });

  it('returns an Axios Error if HTTP code is in 500 range', async () => {
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];

    mock.onPost(`https://localhost:${port}/getsigner`).reply(500);

    try {
      await bissGetSigner(port, { selector: {} });
    } catch (e) {
      const error = e as any;
      expect(error.message).toBe('Request failed with status code 500');
      expect(error.response.status).toBe(500);
      expect(error.stack).toBeTruthy();
      expect(error.config).toBeTruthy();
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });

  it('Returns a custom error if HTTP code is in 200 range, but reasonCode is not in 200 range or status is failed', async () => {
    const BISSErrorResponse = {
      status: 'failed',
      reasonCode: 500,
      reasonText: 'Cannot get signer certificate from BISS',
    };
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];

    mock
      .onPost(`https://localhost:${port}/getsigner`)
      .reply(200, BISSErrorResponse);

    try {
      await bissGetSigner(port, { selector: {} });
    } catch (e) {
      const error = e as any;
      expect(error.message).toBe('Cannot get signer certificate from BISS');
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });

  it('returns a validation error if chain array is empty', async () => {
    const emptyChainSignerData: BISSGetSignerResponse = {
      ...getSignerData,
      chain: [],
    };
    const mock = new MockAdapter(axios);
    const port = BISSPorts[0];

    mock
      .onPost(`https://localhost:${port}/getsigner`)
      .reply(200, emptyChainSignerData);
    try {
      await bissGetSigner(port, { selector: {} });
    } catch (e) {
      const error = e as any;
      expect(error.message).toEqual('No chain certificate for signing');
      expect(mock.history.post.length).toBe(1);
    } finally {
      mock.reset();
    }
  });
});
