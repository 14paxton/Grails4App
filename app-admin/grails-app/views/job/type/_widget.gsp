<g:select name="type.id" required="" id="type"
          from="${app.admin.jobsboard.Type.list()}"
          optionKey="id" optionValue="name"
          value="${bean?.type?.id}"
/>