import { promises as fs } from 'fs';
import path from 'path';

export const cache = {
  set: async (data: any, fileName: string) => {
    return await fs.writeFile(
      path.join(process.cwd(), fileName),
      JSON.stringify(data)
    );
  },
  get: async (fileName: string): Promise<any> => {
    try {
      const data = await fs.readFile(path.join(process.cwd(), fileName));
      const parsedData: any = JSON.parse(data as any);
      return parsedData && parsedData.length > 0 ? parsedData : null;
    } catch (err) {
      console.trace(err);
      return null;
    }
  },
  remove: async (fileName: string) => {
    return await fs.rm(path.join(process.cwd(), fileName));
  },
};
