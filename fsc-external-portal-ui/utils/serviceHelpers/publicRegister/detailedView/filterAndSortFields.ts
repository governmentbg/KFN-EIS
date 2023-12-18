const filterAndSortFields = (
  fields: Record<string, string | number>,
  includedFields: { label: string; order: number }[]
) => {
  const filteredFields = {} as Record<string, string | number>;

  includedFields
    .sort((a, b) => a.order - b.order)
    .forEach(
      ({ label }) =>
        fields[label] !== undefined && (filteredFields[label] = fields[label])
    );

  return filteredFields;
};

export default filterAndSortFields;
