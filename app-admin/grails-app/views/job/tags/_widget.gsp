<g:select name="job.tags" required="" id="tags"
          from="${app.admin.jobsboard.Tag.list()}"
          optionKey="id"
          optionValue="name"
          value="${bean?.tags*.id}"
          multiple="true"
/>
