1.索引新增字段
http://192.168.80.35:9200/media_video_test/_mapping
{
  "properties": {
    "onUser2" : {
      "type" : "long"
    }
  }
}

2.更新字段的值
http://192.168.80.35:9200/media_video_test/_update_by_query
{
  "script": {
    "source": "if (ctx._source.onUser2 == null) {ctx._source.onUser2 = ctx._source.auditUser;}"
  },
  "query": {
    "bool": {
      "must": [
        { "term": { "status": 1 } },
        { "bool": { "must_not": { "exists": { "field": "onUser2" } } } }
      ]
    }
  }
}


{
  "script": {
    "source": """
      if (ctx._source.onUser2 == null) {
        ctx._source.onUser2 = ctx._source.auditUser;
      }
    """
  },
  "query": {
    "bool": {
      "must": [
        { "term": { "status": 1 } },
        { "bool": { "must_not": { "exists": { "field": "onUser2" } } } }
      ]
    }
  }
}

学习资料
https://www.cnblogs.com/jelly12345/p/15007745.html