export const replication_data = {
  "success": true,
  "code": 0,
  "msg": "Success",
  "data": {
    "mode": "REPLICATION",
    "topology_id": 33,
    "task_type": "REPLICATION",
    "source": {
      "total": 2,
      "disconnected": 0,
      "ready": 0,
      "running": 1,
      "aborted": 1,
      "nodes": [
        {
          "id": 1,
          "name": "test_src_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 0
        },
        {
          "id": 2,
          "name": "test_src_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "ABORTED",
          "priority": 1
        }
      ]
    },
    "target": {
      "total": 3,
      "disconnected": 0,
      "ready": 3,
      "running": 0,
      "aborted": 0,
      "nodes": [
        {
          "id": 3,
          "name": "test_trg_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "READY",
          "priority": 0
        },
        {
          "id": 4,
          "name": "test_trg_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "READY",
          "priority": 1
        },
        {
          "id": 5,
          "name": "test_trg_inst3",
          "node_role": null,
          "ha_state": null,
          "status": "READY",
          "priority": 2
        }
      ]
    },
    "status": "RUNNING",
    "throughput": 1145,
    "sync_gap": 0,
    "scn_gap": 1201,
    "insert_per_sec": 650,
    "update_per_sec": 475,
    "delete_per_sec": 20,
    "tx_per_sec": 78,
    "alt_in_day": 2,
    "drp_in_day": 2,
    "trnc_in_day": 2,
    "metrics": [
      {
        "hour": 0,
        "total_dmls": 1879,
        "total_ddls": 7,
        "inserts": 807,
        "updates": 672,
        "deletes": 400,
        "truncates": 2,
        "alters": 4,
        "drops": 1,
        "txs": 59
      },
      {
        "hour": 1,
        "total_dmls": 2114,
        "total_ddls": 4,
        "inserts": 920,
        "updates": 759,
        "deletes": 435,
        "truncates": 1,
        "alters": 3,
        "drops": 0,
        "txs": 66
      },
      {
        "hour": 2,
        "total_dmls": 641,
        "total_ddls": 10,
        "inserts": 299,
        "updates": 306,
        "deletes": 36,
        "truncates": 8,
        "alters": 2,
        "drops": 0,
        "txs": 60
      },
      {
        "hour": 3,
        "total_dmls": 2043,
        "total_ddls": 8,
        "inserts": 740,
        "updates": 956,
        "deletes": 347,
        "truncates": 8,
        "alters": 0,
        "drops": 0,
        "txs": 87
      },
      {
        "hour": 4,
        "total_dmls": 1318,
        "total_ddls": 8,
        "inserts": 810,
        "updates": 433,
        "deletes": 75,
        "truncates": 8,
        "alters": 0,
        "drops": 0,
        "txs": 63
      },
      {
        "hour": 5,
        "total_dmls": 968,
        "total_ddls": 5,
        "inserts": 491,
        "updates": 397,
        "deletes": 80,
        "truncates": 4,
        "alters": 1,
        "drops": 0,
        "txs": 52
      },
      {
        "hour": 6,
        "total_dmls": 1864,
        "total_ddls": 8,
        "inserts": 228,
        "updates": 727,
        "deletes": 909,
        "truncates": 5,
        "alters": 2,
        "drops": 1,
        "txs": 16
      },
      {
        "hour": 7,
        "total_dmls": 2043,
        "total_ddls": 10,
        "inserts": 890,
        "updates": 483,
        "deletes": 670,
        "truncates": 5,
        "alters": 4,
        "drops": 1,
        "txs": 13
      },
      {
        "hour": 8,
        "total_dmls": 1698,
        "total_ddls": 5,
        "inserts": 541,
        "updates": 745,
        "deletes": 412,
        "truncates": 3,
        "alters": 1,
        "drops": 1,
        "txs": 85
      },
      {
        "hour": 9,
        "total_dmls": 1009,
        "total_ddls": 11,
        "inserts": 358,
        "updates": 290,
        "deletes": 361,
        "truncates": 6,
        "alters": 4,
        "drops": 1,
        "txs": 74
      },
      {
        "hour": 10,
        "total_dmls": 2293,
        "total_ddls": 4,
        "inserts": 559,
        "updates": 740,
        "deletes": 994,
        "truncates": 4,
        "alters": 0,
        "drops": 0,
        "txs": 21
      },
      {
        "hour": 11,
        "total_dmls": 2198,
        "total_ddls": 7,
        "inserts": 685,
        "updates": 611,
        "deletes": 902,
        "truncates": 3,
        "alters": 4,
        "drops": 0,
        "txs": 37
      },
      {
        "hour": 12,
        "total_dmls": 2577,
        "total_ddls": 12,
        "inserts": 905,
        "updates": 884,
        "deletes": 788,
        "truncates": 9,
        "alters": 2,
        "drops": 1,
        "txs": 57
      },
      {
        "hour": 13,
        "total_dmls": 1948,
        "total_ddls": 11,
        "inserts": 905,
        "updates": 468,
        "deletes": 575,
        "truncates": 8,
        "alters": 2,
        "drops": 1,
        "txs": 80
      },
      {
        "hour": 14,
        "total_dmls": 1337,
        "total_ddls": 11,
        "inserts": 583,
        "updates": 221,
        "deletes": 533,
        "truncates": 9,
        "alters": 1,
        "drops": 1,
        "txs": 74
      },
      {
        "hour": 15,
        "total_dmls": 1583,
        "total_ddls": 13,
        "inserts": 558,
        "updates": 190,
        "deletes": 835,
        "truncates": 9,
        "alters": 4,
        "drops": 0,
        "txs": 24
      },
      {
        "hour": 16,
        "total_dmls": 1058,
        "total_ddls": 3,
        "inserts": 707,
        "updates": 278,
        "deletes": 73,
        "truncates": 3,
        "alters": 0,
        "drops": 0,
        "txs": 66
      },
      {
        "hour": 17,
        "total_dmls": 1295,
        "total_ddls": 10,
        "inserts": 647,
        "updates": 534,
        "deletes": 114,
        "truncates": 9,
        "alters": 0,
        "drops": 1,
        "txs": 19
      },
      {
        "hour": 18,
        "total_dmls": 1869,
        "total_ddls": 5,
        "inserts": 738,
        "updates": 172,
        "deletes": 959,
        "truncates": 4,
        "alters": 0,
        "drops": 1,
        "txs": 87
      },
      {
        "hour": 19,
        "total_dmls": 1356,
        "total_ddls": 4,
        "inserts": 357,
        "updates": 47,
        "deletes": 952,
        "truncates": 1,
        "alters": 3,
        "drops": 0,
        "txs": 68
      },
      {
        "hour": 20,
        "total_dmls": 1066,
        "total_ddls": 5,
        "inserts": 328,
        "updates": 664,
        "deletes": 74,
        "truncates": 0,
        "alters": 4,
        "drops": 1,
        "txs": 36
      },
      {
        "hour": 21,
        "total_dmls": 1697,
        "total_ddls": 9,
        "inserts": 705,
        "updates": 922,
        "deletes": 70,
        "truncates": 6,
        "alters": 3,
        "drops": 0,
        "txs": 25
      },
      {
        "hour": 22,
        "total_dmls": 1293,
        "total_ddls": 11,
        "inserts": 293,
        "updates": 726,
        "deletes": 274,
        "truncates": 9,
        "alters": 2,
        "drops": 0,
        "txs": 5
      },
      {
        "hour": 23,
        "total_dmls": 3,
        "total_ddls": 0,
        "inserts": 0,
        "updates": 0,
        "deletes": 3,
        "truncates": 0,
        "alters": 0,
        "drops": 0,
        "txs": 1
      }
    ]
  }
}

export const initEnv_data = {
  "success": true,
  "code": 0,
  "msg": "Success",
  "data": {
    "steps": [
      {
        "id": -6968689154324199000,
        "parent_job": 5165120935602986000,
        "sequence": 1,
        "status": "ERROR",
        "step": "STEP 69",
        "message": "This is message for testing"
      },
      {
        "id": -1265098033650737000,
        "parent_job": -3189431745514450000,
        "sequence": 2,
        "status": "RUNNING",
        "step": "STEP 56",
        "message": "This is message for testing"
      },
      {
        "id": -2010653150166632200,
        "parent_job": -3055452261267497500,
        "sequence": 3,
        "status": "STOPPED",
        "step": "STEP 76",
        "message": "This is message for testing"
      },
      {
        "id": 3311740455034514400,
        "parent_job": -8975809901336196000,
        "sequence": 4,
        "status": "RUNNING",
        "step": "STEP 12",
        "message": "This is message for testing"
      },
      {
        "id": -2338693682618967000,
        "parent_job": -827328892956492700,
        "sequence": 5,
        "status": "INITIALIZE",
        "step": "STEP 40",
        "message": "This is message for testing"
      }
    ],
    "mode": "INITENV",
    "topology_id": 33,
    "task_type": "REPLICATION",
    "source": {
      "total": 2,
      "disconnected": 0,
      "ready": 0,
      "running": 2,
      "aborted": 0,
      "nodes": [
        {
          "id": 1,
          "name": "test_src_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 0
        },
        {
          "id": 2,
          "name": "test_src_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 1
        }
      ]
    },
    "target": {
      "total": 3,
      "disconnected": 0,
      "ready": 0,
      "running": 3,
      "aborted": 0,
      "nodes": [
        {
          "id": 3,
          "name": "test_trg_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 0
        },
        {
          "id": 4,
          "name": "test_trg_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 1
        },
        {
          "id": 5,
          "name": "test_trg_inst3",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 2
        }
      ]
    },
    "start_time": "2021-01-11 14:23:38.038",
    "run_time": 2071,
    "unit": "TOPOLOGY_UNIT",
    "status": "RUNNING",
    "message": null
  }
}

export const InitLoad_data = {
  "success": true,
  "code": 0,
  "msg": "Success",
  "data": {
    "mode": "INITLOAD",
    "topology_id": 33,
    "task_type": "REPLICATION",
    "source": {
      "total": 2,
      "disconnected": 0,
      "ready": 0,
      "running": 0,
      "aborted": 2,
      "nodes": [
        {
          "id": 1,
          "name": "test_src_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "ABORTED",
          "priority": 0
        },
        {
          "id": 2,
          "name": "test_src_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "ABORTED",
          "priority": 1
        }
      ]
    },
    "target": {
      "total": 3,
      "disconnected": 0,
      "ready": 0,
      "running": 3,
      "aborted": 0,
      "nodes": [
        {
          "id": 3,
          "name": "test_trg_inst1",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 0
        },
        {
          "id": 4,
          "name": "test_trg_inst2",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 1
        },
        {
          "id": 5,
          "name": "test_trg_inst3",
          "node_role": null,
          "ha_state": null,
          "status": "RUNNING",
          "priority": 2
        }
      ]
    },
    "status": "COMPLETED",
    "start_time": "2021-01-11 14:24:42.042",
    "run_time": 3254,
    "progress": 50,
    "total": 1025,
    "waiting": 406,
    "processing": 7,
    "conflict": 29,
    "error": 9,
    "completed": 567,
    "message": null
  }
}