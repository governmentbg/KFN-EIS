import Grid from '@mui/material/Grid';

const Footer = () => (
  <Grid container>
    <Grid item xs={12} sm={12} md={12} lg={12}>
      Footer component
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      authorship information
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      copyright information
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      contact information
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      sitemap
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      back to top links
    </Grid>
    <Grid item xs={12} sm={6} md={4} lg={4}>
      related documents
    </Grid>
  </Grid>
);

export default Footer;
