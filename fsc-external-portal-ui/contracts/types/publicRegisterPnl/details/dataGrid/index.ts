export type PublicRegisterDetailsSectionColumnDefinition = {
  label: string;
  order: number;
  tableName?: string;
};

export type SectionDataGridViewPropsType = {
  sectionId: number;
  rows: Record<string, string | number>[];
  tableName?: string;
  columnDefinitions: PublicRegisterDetailsSectionColumnDefinition[];
};
export type SectionFieldsViewPropsType = {
  sectionId: number;
  fields: Record<string, string | number>;
  fieldDefinitions: PublicRegisterDetailsSectionColumnDefinition[];
};
