import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';

export const axiosInstance = axios.create({});

const request = async (
  config: Partial<AxiosRequestConfig>
): Promise<any | AxiosError> => {
  const { data } = await axiosInstance.request({
    baseURL: config.baseURL ?? process.env.NEXT_PUBLIC_BASE_URL,
    ...config,
  });

  return data;
};

export default request;
