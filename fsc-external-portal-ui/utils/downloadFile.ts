const downloadFile = (fileName: string, urlData: string) => {
  const aLink = document.createElement('a');

  aLink.download = fileName;
  aLink.href = urlData;

  const event = new MouseEvent('click');

  aLink.dispatchEvent(event);
};

export default downloadFile;
