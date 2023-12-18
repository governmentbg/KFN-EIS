const generateInitialFormValues = (formData: any) => {
  let initialData: any = {};
  if (formData.length > 0) {
    formData.forEach((component: any) => {
      if (component.value === null) {
        initialData[component.name] = !component.multiselect ? '' : [];
      } else {
        initialData[component.name] = component.value;
      }
    });
  }

  return initialData;
};

export default generateInitialFormValues;
