export const restoreSettings = () => {
  let settings = null;

  try {
    const storedData = localStorage.getItem('settings');

    if (storedData) settings = JSON.parse(storedData);
  } catch (error) {
    // If stored data is not a stringified JSON this might fail,
    // that's why we catch the error
  }

  return settings;
};

export const storeSettings = (settings = {}) =>
  localStorage.setItem('settings', JSON.stringify(settings));
