export const repair_data_single = {
  owner: "SR_S",
  object: `LOnggggggLonggggLongggLonggggNAmetest_object${parseInt(Math.random() * 100)}`,
  inconsistent_total: 1000,
  stored_rows: 10,
  total: 4,
  list: [
    {
      ref: 0,
      type: "UPDATE",
      status: "CURRUPTED",
      c1:"test1",
      c2: "test2",
      c3: "test3" 
    }, 
    {
      ref: 1,
      type: "UPDATE",
      status: "CURRUPTED",
      c1:"test4",
      c2: "test5",
      c3: "test6" 
    }, 
    {
      ref: 2,
      type: "UPDATE",
      status: "CURRUPTED",
      c1:"test7",
      c2: "test8",
      c3: "test9" 
    }, 
    {
      ref: 3,
      type: "UPDATE",
      status: "CURRUPTED",
      c1:"test10",
      c2: "test11",
      c3: "test12" 
    }, 
  ]
}

export const repair_data_multiple = {
  total: 3,
  list: [
    {
      ref: 1,
      schema: `test_schema${parseInt(Math.random() * 100)}`,
      object: `test_object${parseInt(Math.random() * 100)}`,
      inconsistent_row: parseInt(Math.random() * 1000),
      stored_row: parseInt(Math.random() * 10),
    },
    {
      ref: 2,
      schema: `test_schema${parseInt(Math.random() * 100)}`,
      object: `test_object${parseInt(Math.random() * 100)}`,
      inconsistent_row: parseInt(Math.random() * 1000),
      stored_row: parseInt(Math.random() * 10),
    },
    {
      ref: 3,
      schema: `test_schema${parseInt(Math.random() * 100)}`,
      object: `test_object${parseInt(Math.random() * 100)}`,
      inconsistent_row: parseInt(Math.random() * 1000),
      stored_row: parseInt(Math.random() * 10),
    },
  ]
} 