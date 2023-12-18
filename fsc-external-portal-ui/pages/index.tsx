import Head from 'next/head';
import Box from '@mui/material/Box';
import homeContentBackground from '../public/homeImages/homeContentBackground.jpg';
import DefaultLayout from '../layouts/DefaultLayout';
import ServicesView from '../views/public/ServicesView';
import { REVALIDATE_TIME } from '../constants';
import { wrapper } from '../store';
import { getAllServices } from '../utils/server/services';

export const getStaticProps = wrapper.getStaticProps((store) => async () => {
  await getAllServices(store);
  return { props: {}, revalidate: REVALIDATE_TIME.DEFAULT };
});

const Home = () => {
  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(${homeContentBackground.src})`,
        backgroundColor: '#ffffff',
        backgroundSize: 'cover',
        backgroundAttachment: 'fixed',
      }}
    >
      <Head>
        <title>Home</title>
      </Head>
      <DefaultLayout>
        <ServicesView />
      </DefaultLayout>
    </Box>
  );
};

export default Home;
