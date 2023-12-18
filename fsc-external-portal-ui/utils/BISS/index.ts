import axios, { AxiosResponse } from 'axios';
import { IDocument } from '../../contracts/interfaces/document';
import { AppDispatch } from '../../store';
import { prepareForSigning, sign } from '../../store/signing';

export interface BISSVersionResponse {
  version: string;
  httpMethods: string;
  contentTypes: string;
  signatureTypes: string;
  selectorAvailable: string;
  hashAlgorithms: string;
}

export type BISSPrepareForSigningResponse = {
  document: IDocument;
  certificateChainBase64: string[];
  fileBase64: string;
  hashBase64: string;
  hashCertBase64: string;
  hashContentBase64: string;
  hashSignBase64: string;
};

export type BISSPrepareForSigningArrayResponse =
  BISSPrepareForSigningResponse[];

export interface BISSSigningStructure {
  filename?: string;
  // in initial call
  certificateChainBase64: string[];
  // deferred signing: 1st phase
  fileBase64?: string;
  hashBase64?: string;
  hashContentBase64?: string;
  hashSignBase64?: string;
  hashCertBase64?: string;

  // deferred signing: 2nd phase
  encryptionAlgorithm?: string;
  signatureBase64?: string;
}

export interface BISSPortResponse {
  port: number;
  versionInfo: BISSVersionResponse;
}

export const BISSPorts = [53952, 53953, 53954, 53955];
const BISSVersionProps = [
  'version',
  'httpMethods',
  'contentTypes',
  'signatureTypes',
  'selectorAvailable',
  'hashAlgorithms',
];

export const bissGetPort = async (): Promise<BISSPortResponse | null> => {
  // recursive version because for loop makes two calls to localhost server
  const result = await _getBISSPort(0);
  return result;
};

const _getBISSPort = async (i: number): Promise<BISSPortResponse | null> => {
  if (i < 0 || i >= BISSPorts.length) {
    // all ports has been checked
    return null;
  }
  const BISSVersionResponse = await isBISSRunningOnPort(BISSPorts[i]);
  if (BISSVersionResponse) {
    const result = {
      port: BISSPorts[i],
      versionInfo: BISSVersionResponse,
    };
    return result as BISSPortResponse;
  } else {
    // try with next port untill all ports in array were checked
    const result = await _getBISSPort(i + 1);
    return result;
  }
};

const isBISSRunningOnPort = async (
  port: number
): Promise<BISSVersionResponse | null> => {
  //this axios local instance is done because the global headers cannot be overwritten
  const localAxios = axios.create();
  localAxios.defaults.headers.common = {
    'Content-Type': 'application/json;charset=UTF-8',
  };
  try {
    var response = await localAxios.get(`https://localhost:${port}/version`, {
      timeout: 1000,
    });
    const result = response ? response.data : null;
    if (result && _isValidBISSResponse(result as BISSVersionResponse)) {
      return result as BISSVersionResponse;
    } else {
      return null;
    }
  } catch (error) {
    return null;
  }
};

const _isValidBISSResponse = (response: BISSVersionResponse): boolean => {
  for (const prop of BISSVersionProps) {
    if (!response.hasOwnProperty(prop)) {
      return false;
    }
  }
  return true;
};

export const BISSSignatureType_Signature: string = 'signature';
export const BISSContentType_Data: string = 'data';
export const BISSContentType_Digest: string = 'digest';

export interface BISSSignParams {
  version?: string;
  contents: string[];
  signedContents: string[];
  signedContentsCert: string[];
  contentType: string;
  hashAlgorithm?: string;
  signatureType?: string;
  signerCertificateB64: string;
  confirmText?: string[];
}

export interface BISSSignResponse {
  status: string;
  reasonCode: string;
  reasonText: string;
  signatures?: string[];
  signatureType?: string;
}

export interface BISSSignError {
  status: string;
  errorMessage: string;
}

export const BISSSignResponseProps: BISSSignResponse = {
  status: '',
  reasonCode: '',
  reasonText: '',
  signatures: [],
};

export const bissSign = async (
  port: number,
  params: BISSSignParams
): Promise<BISSSignResponse> => {
  var localAxios = axios.create();
  localAxios.defaults.headers.common = {
    'Content-Type': 'application/json;charset=UTF-8',
  };
  const response = await localAxios.post(
    `https://localhost:${port}/sign`,
    params
  );
  return _validateSignResponse(response.data, response);
};

const _validateSignResponse = (
  data: BISSSignResponse,
  axiosResponse: AxiosResponse
): BISSSignResponse => {
  if (data.hasOwnProperty('status') && data.status === 'ok') {
    if (data.signatures && data.signatures.length !== 0) {
      return data;
    } else {
      throw new Error('no signatures in response');
    }
  } else if (data.hasOwnProperty('errorMessage')) {
    throw new Error(data.reasonCode + ': ' + data.reasonText);
  } else {
    throw axiosResponse;
  }
};

export interface BISSGetSignerParams {
  selector: {
    issuers?: [];
    akis?: [];
    keyusages?: [];
  };
  showValidCerts?: boolean;
}

export interface BISSGetSignerResponse {
  status: string;
  reasonCode: string;
  reasonText: string;
  chain: string[];
}

export interface BISSGetSignerError {
  status: string;
  errorMessage: string;
}

export const GetSignerResponseProps: BISSGetSignerResponse = {
  status: '',
  reasonCode: '',
  reasonText: '',
  chain: [],
};

export const bissGetSigner = async (
  port: number,
  params: BISSGetSignerParams
): Promise<BISSGetSignerResponse> => {
  var localAxios = axios.create();
  localAxios.defaults.headers.common = {
    'Content-Type': 'application/json;charset=UTF-8',
  };
  const response = await localAxios.post(
    `https://localhost:${port}/getsigner`,
    params,
    { timeout: 60000 }
  );
  const signResponse = _validateSignerResponse(response.data);
  return signResponse;
};

const _validateSignerResponse = (
  response: BISSGetSignerResponse | BISSGetSignerError
): BISSGetSignerResponse => {
  if (response.hasOwnProperty('status') && response.status === 'ok') {
    const signResponse = response as BISSGetSignerResponse;
    if (
      signResponse.hasOwnProperty('chain') &&
      signResponse.chain &&
      signResponse.chain.length > 0
    ) {
      return response as BISSGetSignerResponse;
    } else {
      throw new Error('No chain certificate for signing');
    }
  } else if (response.hasOwnProperty('errorMessage')) {
    throw new Error((response as BISSGetSignerError).errorMessage);
  } else {
    throw new Error('Cannot get signer certificate from BISS');
  }
};

export const signBISS = async (
  bissPort: BISSPortResponse,
  dispatch: AppDispatch,
  fetchDocumentFile: (args: {
    document: IDocument;
    serviceRequestId?: number;
  }) => Promise<any>,
  serviceRequestId: number,
  accessToken: string
) => {
  const signerParams: BISSGetSignerParams = {
    selector: {},
    showValidCerts: true,
  };

  const bissSigner: BISSGetSignerResponse = await bissGetSigner(
    bissPort.port,
    signerParams
  );

  const certificateChain: string[] = bissSigner.chain;

  const prepareForSigningArrayResponse = await dispatch(
    prepareForSigning({
      certificateChain,
      serviceRequestId,
      accessToken,
      createPdf: true,
    })
  ).unwrap();

  if (
    !Array.isArray(prepareForSigningArrayResponse) ||
    prepareForSigningArrayResponse.length === 0
  ) {
    throw new Error('No response from prepare for signing');
  }

  const reader = new FileReader();

  for (const signingPreparationResponse of prepareForSigningArrayResponse) {
    const { document, ...bissSigningStructure } = signingPreparationResponse;

    const { contentType: fileContentType } = document.fileRef;
    if (!fileContentType)
      throw new Error('Content type for this document is missing');

    if (!isFileContentTypeSignable(fileContentType))
      throw new Error('Content type for this document is not signable');

    const triggerSignOnFileLoad = async () => {
      const handleFileLoad = new Promise((resolve, reject) => {
        reader.onload = async function () {
          try {
            let base64 = reader.result as string;
            const prefix = `data:${fileContentType};base64,`;
            if (base64.startsWith(prefix)) {
              base64 = base64.substring(prefix.length);
            }

            const bissSignParams = {
              version: '1.0',
              contents: [bissSigningStructure.hashContentBase64],
              signedContents: [bissSigningStructure.hashSignBase64],
              signedContentsCert: [bissSigningStructure.hashCertBase64],
              contentType: BISSContentType_Data,
              hashAlgorithm: 'SHA256',
              signatureType: BISSSignatureType_Signature,
              signerCertificateB64:
                bissSigningStructure.certificateChainBase64[0],
              confirmText: ['hash'],
            } as BISSSignParams;

            const bissSignResponse: BISSSignResponse = await bissSign(
              bissPort!.port,
              bissSignParams
            );

            if (
              bissSignResponse.signatures == null ||
              bissSignResponse.signatures.length <= 0
            )
              throw new Error('No signatures returned from BISS');

            const signResponse = await dispatch(
              sign({
                bissSigningStructure: {
                  ...bissSigningStructure,
                  encryptionAlgorithm: bissSignResponse.signatureType,
                  signatureBase64: bissSignResponse.signatures[0],
                  filename: document.fileRef.fileName,
                },
                documentId: document.id,
                serviceRequestId,
                accessToken,
              })
            ).unwrap();

            resolve(signResponse);
          } catch (e) {
            reject(e);
          }
        };

        reader.onerror = (error) => reject(error);
      });

      return await handleFileLoad;
    };

    const file = await fetchDocumentFile({ document, serviceRequestId });

    await reader.readAsDataURL(new Blob([file], { type: fileContentType }));

    await triggerSignOnFileLoad();
  }
};

export const isFileContentTypeSignable = (fileContentType: string): boolean =>
  ['application/pdf', 'text/xml', 'application/xbrl+xml'].find(
    (signableContentType) => signableContentType === fileContentType
  )
    ? true
    : false;
