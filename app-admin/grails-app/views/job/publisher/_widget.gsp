<g:select name="publisher.id" required="" id="pubisher"
          from="${app.admin.jobsboard.Publisher.list()}"
          optionKey="id" optionValue="name"
          value="${bean?.publisher?.id}"
/>